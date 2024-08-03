package com.iefjt.android.ui.home.elementlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iefjt.android.domain.model.Element
import com.iefjt.android.domain.usecase.elements.DeleteElementUseCase
import com.iefjt.android.domain.usecase.elements.GetAllElementsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ElementListViewModel @Inject constructor(
    private val getAllElementsUseCase: GetAllElementsUseCase,
    private val deleteElementUseCase: DeleteElementUseCase
) :
    ViewModel() {
    var uiState by mutableStateOf(ElementsUiState())
        private set

    init {
        getElements()
    }

    private fun getElements() {
        viewModelScope.launch {
            getAllElementsUseCase().catch {
                uiState = uiState.copy(
                    exception = it as Exception,
                    loading = false
                )
            }.collect {
                uiState = uiState.copy(elements = it, loading = false)
            }
        }
    }

    fun onDeleteElement(elementId: String) {
        viewModelScope.launch {
            deleteElementUseCase(elementId)
        }
    }
}

data class ElementsUiState(
    val exception: Exception? = null,
    val typeId: String = "",
    val loading: Boolean = true,
    val elements: List<Element> = emptyList()
)