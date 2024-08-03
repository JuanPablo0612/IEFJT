package com.iefjt.android.ui.editelement

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iefjt.android.domain.model.User
import com.iefjt.android.domain.usecase.elements.DeleteElementUseCase
import com.iefjt.android.domain.usecase.elements.GetElementByIdUseCase
import com.iefjt.android.domain.usecase.users.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditElementViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getElementByIdUseCase: GetElementByIdUseCase,
    private val deleteElementUseCase: DeleteElementUseCase
) : ViewModel() {
    var uiState by mutableStateOf(ElementDetailsUiState())
        private set

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            uiState = getCurrentUserUseCase().fold(
                onSuccess = {
                    uiState.copy(user = it)
                },
                onFailure = {
                    uiState.copy(exception = it as Exception)
                }
            )
        }
    }

    fun getElement(elementId: String) {
        getElementById(elementId)
    }

    private fun getElementById(elementId: String) {
        viewModelScope.launch {
            uiState = getElementByIdUseCase(elementId).fold(
                onSuccess = {
                    uiState.copy(
                        name = it.name,
                        isValidName = true,
                        type = it.type,
                        isValidType = true,
                        brand = it.brand,
                        isValidBrand = true,
                        serial = it.serial,
                        isValidSerial = true,
                        status = it.status,
                        isValidStatus = true,
                        headquarters = it.headquarters,
                        isValidHeadquarters = true,
                        observations = it.observations,
                        isValidObservations = true,
                        elementId = elementId
                    )
                },
                onFailure = {
                    uiState.copy(exception = it as Exception)
                }
            )
        }
    }

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

    fun onEditElement() {

    }

    fun deleteElement() {
        viewModelScope.launch {
            uiState = deleteElementUseCase(uiState.elementId).fold(
                onSuccess = {
                    uiState.copy(deleted = true)
                },
                onFailure = {
                    uiState.copy(exception = it as Exception)
                }
            )
        }
    }
}

data class ElementDetailsUiState(
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
    val isValidObservations: Boolean = false,
    val user: User = User(),
    val deleted: Boolean = false,
    val elementId: String = ""
)