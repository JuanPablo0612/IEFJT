package com.iefjt.android.ui.addelement

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iefjt.android.domain.usecase.elements.AddElementUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddElementViewModel @Inject constructor(private val addElementUseCase: AddElementUseCase) :
    ViewModel() {
    var uiState by mutableStateOf(AddElementUiState())
        private set

    fun onNameChange(text: String) {
        val isValidName = text.isNotBlank()
        uiState = uiState.copy(name = text, isValidName = isValidName)
    }

    fun onSerialChange(text: String) {
        val isValidSerial = true
        uiState = uiState.copy(serial = text)
    }

    fun onObservationsChange(text: String) {
        val isValidObservations = text.isNotBlank()
        uiState = uiState.copy(observations = text, isValidObservations = isValidObservations)
    }

    fun onTypeChange(text: String) {
        val isValidType = text.isNotBlank()
        uiState = uiState.copy(type = text, isValidType = isValidType)
    }

    fun onBrandChange(text: String) {
        val isValidBrand = text.isNotBlank()
        uiState = uiState.copy(brand = text, isValidBrand = isValidBrand)
    }

    fun onStatusChange(text: String) {
        val isValidStatus = text.isNotBlank()
        uiState = uiState.copy(status = text, isValidStatus = isValidStatus)
    }

    fun onHeadquartersChange(text: String) {
        val isValidHeadquarters = text.isNotBlank()
        uiState = uiState.copy(headquarters = text, isValidHeadquarters = isValidHeadquarters)
    }

    fun onAddElement() {
        viewModelScope.launch {
            with(uiState) {
                uiState = addElementUseCase(
                    name = name,
                    type = type,
                    brand = brand,
                    serial = serial,
                    status = status,
                    headquarters = headquarters,
                    observations = observations
                ).fold(
                    onSuccess = { uiState.copy(successful = true) },
                    onFailure = { uiState.copy(exception = it as Exception) }
                )
            }
        }
    }
}

data class AddElementUiState(
    val exception: Exception? = null,
    val successful: Boolean = false,
    val name: String = "",
    val isValidName: Boolean = false,
    val type: String = "",
    val isValidType: Boolean = false,
    val brand: String = "",
    val isValidBrand: Boolean = false,
    val serial: String = "",
    val isValidSerial: Boolean = false,
    val status: String = "",
    val isValidStatus: Boolean = false,
    val headquarters: String = "",
    val isValidHeadquarters: Boolean = false,
    val observations: String = "",
    val isValidObservations: Boolean = false
)