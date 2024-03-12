package com.iefjt.android.ui.inventory.type

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iefjt.android.data.exceptions.getMessageId
import com.iefjt.android.domain.model.Type
import com.iefjt.android.domain.usecase.types.AddTypeUseCase
import com.iefjt.android.domain.usecase.types.DeleteTypeUseCase
import com.iefjt.android.domain.usecase.types.GetAllTypesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TypesViewModel @Inject constructor(
    private val addTypeUseCase: AddTypeUseCase,
    private val deleteTypeUseCase: DeleteTypeUseCase,
    private val getAllTypesUseCase: GetAllTypesUseCase
) : ViewModel() {
    var uiState by mutableStateOf(TypesUiState())
        private set

    init {
        getAllTypes()
    }

    fun onShowAddTypeDialogChange() {
        uiState = uiState.copy(showAddTypeDialog = !uiState.showAddTypeDialog)
    }

    private fun resetAddTypeDialog() {
        uiState = uiState.copy(
            errorMessageId = null,
            showAddTypeDialog = false,
            addTypeDialogName = "",
            isValidAddTypeDialogName = false,
            addTypeDialogImageUrl = "",
            isValidAddTypeDialogImageUrl = false
        )
    }

    fun onAddTypeDialogNameChange(text: String) {
        uiState = uiState.copy(addTypeDialogName = text)
        validateAddTypeDialogName()
    }

    private fun validateAddTypeDialogName() {
        val isValid = uiState.addTypeDialogName.isNotBlank()
        uiState = uiState.copy(isValidAddTypeDialogName = isValid)
    }

    fun onAddTypeDialogImageUrlChange(text: String) {
        uiState = uiState.copy(addTypeDialogImageUrl = text)
        validateAddTypeDialogImageUrl()
    }

    private fun validateAddTypeDialogImageUrl() {
        val isValid = Patterns.WEB_URL.matcher(uiState.addTypeDialogImageUrl).matches()
        uiState = uiState.copy(isValidAddTypeDialogImageUrl = isValid)
    }

    private fun getAllTypes() {
        viewModelScope.launch {
            getAllTypesUseCase()
                .catch { cause ->
                    uiState = uiState.copy(errorMessageId = (cause as Exception).getMessageId())
                }
                .collect { types ->
                    uiState = uiState.copy(types = types)
                }
        }
    }

    fun addType() {
        viewModelScope.launch {
            try {
                addTypeUseCase(uiState.addTypeDialogName, uiState.addTypeDialogImageUrl)
                resetAddTypeDialog()
            } catch (e: Exception) {
                uiState = uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }

    fun deleteType(typeId: String) {
        viewModelScope.launch {
            try {
                deleteTypeUseCase(typeId)
            } catch (e: Exception) {
                uiState = uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }
}

data class TypesUiState(
    val errorMessageId: Int? = null,
    val showAddTypeDialog: Boolean = false,
    val addTypeDialogName: String = "",
    val isValidAddTypeDialogName: Boolean = false,
    val addTypeDialogImageUrl: String = "",
    val isValidAddTypeDialogImageUrl: Boolean = false,
    val types: List<Type> = emptyList()
)