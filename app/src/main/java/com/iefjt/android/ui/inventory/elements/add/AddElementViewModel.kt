package com.iefjt.android.ui.inventory.elements.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iefjt.android.data.exceptions.getMessageId
import com.iefjt.android.domain.model.Brand
import com.iefjt.android.domain.model.Headquarters
import com.iefjt.android.domain.model.Status
import com.iefjt.android.domain.model.Type
import com.iefjt.android.domain.usecase.brands.GetBrandByIdUseCase
import com.iefjt.android.domain.usecase.elements.AddElementUseCase
import com.iefjt.android.domain.usecase.headquarters.GetHeadquartersByIdUseCase
import com.iefjt.android.domain.usecase.statuses.GetStatusByIdUseCase
import com.iefjt.android.domain.usecase.types.GetTypeByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddElementViewModel @Inject constructor(
    private val addElementUseCase: AddElementUseCase,
    private val getBrandByIdUseCase: GetBrandByIdUseCase,
    private val getHeadquartersByIdUseCase: GetHeadquartersByIdUseCase,
    private val getStatusByIdUseCase: GetStatusByIdUseCase,
    private val getTypeByIdUseCase: GetTypeByIdUseCase
) : ViewModel() {
    var uiState by mutableStateOf(AddElementUiState())
        private set

    fun onNameChange(text: String) {
        uiState = uiState.copy(name = text)
        validateName()
    }

    private fun validateName() {
        val isValid = uiState.name.isNotBlank()
        uiState = uiState.copy(isValidName = isValid)
    }

    fun onSerialChange(text: String) {
        uiState = uiState.copy(serial = text)
    }

    fun onObservationsChange(text: String) {
        uiState = uiState.copy(observations = text)
    }

    fun onTypeSelect(typeId: String) {
        uiState = uiState.copy(typeId = typeId)
        getType()
    }

    fun onBrandSelect(brandId: String) {
        uiState = uiState.copy(brandId = brandId)
        getBrand()
    }

    fun onStatusSelect(statusId: String) {
        uiState = uiState.copy(statusId = statusId)
        getStatus()
    }

    fun onHeadquartersSelect(headquartersId: String) {
        uiState = uiState.copy(headquartersId = headquartersId)
        getHeadquarters()
    }

    private fun getType() {
        viewModelScope.launch {
            uiState = try {
                val type = getTypeByIdUseCase(uiState.typeId)
                uiState.copy(type = type)
            } catch (e: Exception) {
                uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }

    private fun getBrand() {
        viewModelScope.launch {
            uiState = try {
                val brand = getBrandByIdUseCase(uiState.brandId)
                uiState.copy(brand = brand)
            } catch (e: Exception) {
                uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }

    private fun getStatus() {
        viewModelScope.launch {
            uiState = try {
                val status = getStatusByIdUseCase(uiState.statusId)
                uiState.copy(status = status)
            } catch (e: Exception) {
                uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }

    private fun getHeadquarters() {
        viewModelScope.launch {
            uiState = try {
                val headquarters = getHeadquartersByIdUseCase(uiState.headquartersId)
                uiState.copy(headquarters = headquarters)
            } catch (e: Exception) {
                uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }

    fun addElement() {
        viewModelScope.launch {
            with(uiState) {
                uiState = try {
                    addElementUseCase(name, typeId, brandId, serial, statusId, headquartersId, observations)
                    uiState.copy(successful = true)
                } catch (e: Exception) {
                    uiState.copy(errorMessageId = e.getMessageId())
                }
            }
        }
    }
}

data class AddElementUiState(
    val errorMessageId: Int? = null,
    val successful: Boolean = false,
    val name: String = "",
    val isValidName: Boolean = false,
    val typeId: String = "",
    val brandId: String = "",
    val serial: String = "",
    val statusId: String = "",
    val headquartersId: String = "",
    val observations: String = "",
    val type: Type? = null,
    val brand: Brand? = null,
    val status: Status? = null,
    val headquarters: Headquarters? = null
)