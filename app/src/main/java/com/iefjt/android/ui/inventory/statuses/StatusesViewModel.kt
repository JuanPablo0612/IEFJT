package com.iefjt.android.ui.inventory.statuses

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iefjt.android.data.exceptions.getMessageId
import com.iefjt.android.domain.model.Status
import com.iefjt.android.domain.usecase.statuses.AddStatusUseCase
import com.iefjt.android.domain.usecase.statuses.DeleteStatusUseCase
import com.iefjt.android.domain.usecase.statuses.GetAllStatusesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatusesViewModel @Inject constructor(
    private val addStatusUseCase: AddStatusUseCase,
    private val deleteStatusUseCase: DeleteStatusUseCase,
    private val getAllStatusesUseCase: GetAllStatusesUseCase
) : ViewModel() {
    var uiState by mutableStateOf(StatusesUiState())
        private set

    init {
        getAllStatuses()
    }

    fun onShowAddStatusDialogChange() {
        uiState = uiState.copy(showAddStatusDialog = !uiState.showAddStatusDialog)
    }

    fun resetAddStatusDialog() {
        uiState = uiState.copy(
            errorMessageId = null,
            showAddStatusDialog = false,
            addStatusDialogName = "",
            isValidAddStatusDialogName = false,
            addStatusDialogColor = "",
            isValidAddStatusDialogColor = false
        )
    }

    fun onAddStatusDialogNameChange(text: String) {
        uiState = uiState.copy(addStatusDialogName = text)
        validateAddStatusDialogName()
    }

    private fun validateAddStatusDialogName() {
        val isValid = uiState.addStatusDialogName.isNotBlank()
        uiState = uiState.copy(isValidAddStatusDialogName = isValid)
    }

    fun onAddStatusDialogColorChange(text: String) {
        uiState = uiState.copy(addStatusDialogColor = text)
        validateAddStatusDialogColor()
    }

    private fun validateAddStatusDialogColor() {
        val isValidLength = uiState.addStatusDialogColor.length == 6
        val regex = "[0-9A-Fa-f]+"
        val isValidColor = uiState.addStatusDialogColor.matches(regex.toRegex())
        uiState = uiState.copy(isValidAddStatusDialogColor = isValidLength && isValidColor)
    }

    private fun getAllStatuses() {
        viewModelScope.launch {
            getAllStatusesUseCase()
                .catch { cause ->
                    uiState = uiState.copy(errorMessageId = (cause as Exception).getMessageId())
                }
                .collect { statuses ->
                    uiState = uiState.copy(statuses = statuses)
                }
        }
    }

    fun addStatus() {
        viewModelScope.launch {
            try {
                addStatusUseCase(
                    name = uiState.addStatusDialogName,
                    color = uiState.addStatusDialogColor
                )
                resetAddStatusDialog()
            } catch (e: Exception) {
                uiState = uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }

    fun deleteStatus(statusId: String) {
        viewModelScope.launch {
            try {
                deleteStatusUseCase(statusId)
            } catch (e: Exception) {
                uiState = uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }
}

data class StatusesUiState(
    val errorMessageId: Int? = null,
    val showAddStatusDialog: Boolean = false,
    val addStatusDialogName: String = "",
    val isValidAddStatusDialogName: Boolean = false,
    val addStatusDialogColor: String = "",
    val isValidAddStatusDialogColor: Boolean = false,
    val statuses: List<Status> = emptyList()
)