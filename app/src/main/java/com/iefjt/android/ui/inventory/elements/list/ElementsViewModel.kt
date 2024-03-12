package com.iefjt.android.ui.inventory.elements.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iefjt.android.data.exceptions.getMessageId
import com.iefjt.android.domain.model.ElementWithAllData
import com.iefjt.android.domain.usecase.elements.GetAllElementsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ElementsViewModel @Inject constructor(private val getAllElementsUseCase: GetAllElementsUseCase) :
    ViewModel() {
    var uiState by mutableStateOf(ElementsUiState())
        private set

    init {
        getAllElements()
    }

    private fun getAllElements() {
        viewModelScope.launch {
            getAllElementsUseCase()
                .catch { cause ->
                    uiState = uiState.copy(errorMessageId = (cause as Exception).getMessageId())
                }
                .collect { elements ->
                    uiState = uiState.copy(elements = elements)
                }
        }
    }
}

data class ElementsUiState(
    val errorMessageId: Int? = null,
    val elements: List<ElementWithAllData> = emptyList()
)