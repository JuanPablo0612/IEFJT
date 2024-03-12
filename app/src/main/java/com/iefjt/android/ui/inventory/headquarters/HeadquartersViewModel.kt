package com.iefjt.android.ui.inventory.headquarters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iefjt.android.data.exceptions.getMessageId
import com.iefjt.android.domain.model.Headquarters
import com.iefjt.android.domain.usecase.headquarters.AddHeadquartersUseCase
import com.iefjt.android.domain.usecase.headquarters.DeleteHeadquartersUseCase
import com.iefjt.android.domain.usecase.headquarters.GetAllHeadquartersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeadquartersViewModel @Inject constructor(private val addHeadquartersUseCase: AddHeadquartersUseCase, private val deleteHeadquartersUseCase: DeleteHeadquartersUseCase, private val getAllHeadquartersUseCase: GetAllHeadquartersUseCase) : ViewModel() {
    var uiState by mutableStateOf(HeadquartersUiState())
        private set

    init {
        getAllHeadquarters()
    }

    fun onShowAddHeadquartersDialogChange() {
        uiState = uiState.copy(showAddHeadquartersDialog = !uiState.showAddHeadquartersDialog)
    }

    private fun resetAddHeadquartersDialog() {
        uiState = uiState.copy(
            errorMessageId = null,
            showAddHeadquartersDialog = false,
            addHeadquartersDialogName = "",
            isValidAddHeadquartersDialogName = false,
        )
    }

    fun onAddHeadquartersDialogNameChange(text: String) {
        uiState = uiState.copy(addHeadquartersDialogName = text)
        validateAddHeadquartersDialogName()
    }

    private fun validateAddHeadquartersDialogName() {
        val isValid = uiState.addHeadquartersDialogName.isNotBlank()
        uiState = uiState.copy(isValidAddHeadquartersDialogName = isValid)
    }

    private fun getAllHeadquarters() {
        viewModelScope.launch {
            getAllHeadquartersUseCase()
                .catch { cause ->
                    uiState = uiState.copy(errorMessageId = (cause as Exception).getMessageId())
                }
                .collect { headquarters ->
                    uiState = uiState.copy(headquarters = headquarters)
                }
        }
    }

    fun addHeadquarters() {
        viewModelScope.launch {
            try {
                addHeadquartersUseCase(uiState.addHeadquartersDialogName)
                resetAddHeadquartersDialog()
            } catch (e: Exception) {
                uiState = uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }

    fun deleteHeadquarters(headquartersId: String) {
        viewModelScope.launch {
            try {
                deleteHeadquartersUseCase(headquartersId)
            } catch (e: Exception) {
                uiState = uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }
}

data class HeadquartersUiState(
    val errorMessageId: Int? = null,
    val showAddHeadquartersDialog: Boolean = false,
    val addHeadquartersDialogName: String = "",
    val isValidAddHeadquartersDialogName: Boolean = false,
    val headquarters: List<Headquarters> = emptyList()
)