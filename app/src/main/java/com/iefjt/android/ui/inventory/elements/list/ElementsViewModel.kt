package com.iefjt.android.ui.inventory.elements.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iefjt.android.data.exceptions.getMessageId
import com.iefjt.android.domain.model.Brand
import com.iefjt.android.domain.model.ElementWithAllData
import com.iefjt.android.domain.model.Headquarters
import com.iefjt.android.domain.model.Status
import com.iefjt.android.domain.model.Type
import com.iefjt.android.domain.usecase.brands.GetBrandByIdUseCase
import com.iefjt.android.domain.usecase.elements.GetAllElementsUseCase
import com.iefjt.android.domain.usecase.headquarters.GetHeadquartersByIdUseCase
import com.iefjt.android.domain.usecase.statuses.GetStatusByIdUseCase
import com.iefjt.android.domain.usecase.types.GetTypeByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ElementsViewModel @Inject constructor(
    private val getAllElementsUseCase: GetAllElementsUseCase,
    private val getBrandByIdUseCase: GetBrandByIdUseCase,
    private val getHeadquartersByIdUseCase: GetHeadquartersByIdUseCase,
    private val getStatusByIdUseCase: GetStatusByIdUseCase,
    private val getTypesByIdUseCase: GetTypeByIdUseCase
) : ViewModel() {
    var uiState by mutableStateOf(ElementsUiState())
        private set

    init {
        getAllElements()
    }

    fun onFiltersDialogVisibilityChange() {
        uiState = uiState.copy(filtersDialogVisible = !uiState.filtersDialogVisible)
    }

    fun onFiltersVisibilityChange(
        brandFilterVisible: Boolean,
        headquartersFilterVisible: Boolean,
        statusFilterVisible: Boolean,
        typeFilterVisible: Boolean
    ) {
        uiState = uiState.copy(
            brandFilterVisible = brandFilterVisible,
            headquartersFilterVisible = headquartersFilterVisible,
            statusFilterVisible = statusFilterVisible,
            typeFilterVisible = typeFilterVisible
        )
    }

    fun onBrandFilterChange(brandId: String) {
        uiState = uiState.copy(brandFilterId = brandId)
        getBrand()
    }

    private fun getBrand() {
        viewModelScope.launch {
            uiState = try {
                val brand = getBrandByIdUseCase(uiState.brandFilterId)
                uiState.copy(brand = brand)
            } catch (e: Exception) {
                uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }

    fun onHeadquartersFilterChange(headquartersId: String) {
        uiState = uiState.copy(headquartersFilterId = headquartersId)
        getHeadquarters()
    }

    private fun getHeadquarters() {
        viewModelScope.launch {
            uiState = try {
                val headquarters = getHeadquartersByIdUseCase(uiState.headquartersFilterId)
                uiState.copy(headquarters = headquarters)
            } catch (e: Exception) {
                uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }

    fun onStatusFilterChange(statusId: String) {
        uiState = uiState.copy(statusFilterId = statusId)
        getStatus()
    }

    private fun getStatus() {
        viewModelScope.launch {
            uiState = try {
                val status = getStatusByIdUseCase(uiState.statusFilterId)
                uiState.copy(status = status)
            } catch (e: Exception) {
                uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }

    fun onTypeFilterChange(typeId: String) {
        uiState = uiState.copy(typeFilterId = typeId)
        getType()
    }

    private fun getType() {
        viewModelScope.launch {
            uiState = try {
                val type = getTypesByIdUseCase(uiState.typeFilterId)
                uiState.copy(type = type)
            } catch (e: Exception) {
                uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }

    private fun getAllElements() {
        viewModelScope.launch {
            getAllElementsUseCase()
                .catch { cause ->
                    uiState = uiState.copy(
                        errorMessageId = (cause as Exception).getMessageId(),
                        loading = false
                    )
                }
                .collect { elements ->
                    uiState = uiState.copy(loading = false, elements = elements)
                }
        }
    }
}

data class ElementsUiState(
    val errorMessageId: Int? = null,
    val filtersDialogVisible: Boolean = false,
    val brandFilterVisible: Boolean = false,
    val headquartersFilterVisible: Boolean = false,
    val statusFilterVisible: Boolean = false,
    val typeFilterVisible: Boolean = false,
    val brandFilterId: String = "",
    val headquartersFilterId: String = "",
    val statusFilterId: String = "",
    val typeFilterId: String = "",
    val brand: Brand? = null,
    val headquarters: Headquarters? = null,
    val status: Status? = null,
    val type: Type? = null,
    val loading: Boolean = true,
    val elements: List<ElementWithAllData> = emptyList()
)