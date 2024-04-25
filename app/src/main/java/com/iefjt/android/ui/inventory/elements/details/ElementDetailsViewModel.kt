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
            uiState = try {
                val element = getElementByIdUseCase(elementId)
                uiState.copy(loading = false, element = element)
            } catch (e :Exception) {
                uiState.copy(errorMessageId = e.getMessageId(), loading = false)
            }
        }
    }

    fun deleteElement() {
        viewModelScope.launch {
            uiState = try {
                deleteElementUseCase(uiState.element!!.id)
                uiState.copy(deleted = true)
            } catch (e: Exception) {
                uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }
}

data class ElementDetailsUiState(
    val errorMessageId: Int? = null,
    val loading: Boolean = true,
    val deleted: Boolean = false,
    val element: ElementWithAllData? = null
)