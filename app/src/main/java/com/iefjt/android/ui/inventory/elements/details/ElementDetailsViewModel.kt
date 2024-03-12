package com.iefjt.android.ui.inventory.elements.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iefjt.android.data.exceptions.getMessageId
import com.iefjt.android.domain.model.ElementWithAllData
import com.iefjt.android.domain.usecase.elements.DeleteElementUseCase
import com.iefjt.android.domain.usecase.elements.GetElementByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ElementDetailsViewModel @Inject constructor(
    private val getElementByIdUseCase: GetElementByIdUseCase,
    private val deleteElementUseCase: DeleteElementUseCase
) :
    ViewModel() {
    var uiState by mutableStateOf(ElementDetailsUiState())
        private set

    fun init(elementId: String) {
        getElementById(elementId)
    }

    private fun getElementById(elementId: String) {
        viewModelScope.launch {
            getElementByIdUseCase(elementId)
                .catch { cause ->
                    uiState = uiState.copy(errorMessageId = (cause as Exception).getMessageId())
                }
                .collect { element ->
                    uiState = uiState.copy(element = element)
                }
        }
    }

    fun deleteElement() {
        viewModelScope.launch {
            try {
                deleteElementUseCase(uiState.element!!.id)
                uiState = uiState.copy(deleted = true)
            } catch (e: Exception) {
                uiState = uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }
}

data class ElementDetailsUiState(
    val errorMessageId: Int? = null,
    val deleted: Boolean = false,
    val element: ElementWithAllData? = null
)