package com.iefjt.android.ui.inventory.brands

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iefjt.android.data.exceptions.getMessageId
import com.iefjt.android.domain.model.Brand
import com.iefjt.android.domain.usecase.brands.AddBrandUseCase
import com.iefjt.android.domain.usecase.brands.DeleteBrandUseCase
import com.iefjt.android.domain.usecase.brands.GetAllBrandsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrandsViewModel @Inject constructor(
    private val addBrandUseCase: AddBrandUseCase,
    private val deleteBrandUseCase: DeleteBrandUseCase,
    private val getAllBrandsUseCase: GetAllBrandsUseCase
) : ViewModel() {
    var uiState by mutableStateOf(BrandsUiState())
        private set

    init {
        getAllBrands()
    }

    fun onShowAddBrandDialogChange() {
        uiState = uiState.copy(showAddBrandDialog = !uiState.showAddBrandDialog)
    }

    private fun resetAddBrandDialog() {
        uiState = uiState.copy(
            errorMessageId = null,
            showAddBrandDialog = false,
            addBrandDialogName = "",
            isValidAddBrandDialogName = false,
            addBrandDialogImageUrl = "",
            isValidAddBrandDialogImageUrl = false
        )
    }

    fun onAddBrandDialogNameChange(text: String) {
        uiState = uiState.copy(addBrandDialogName = text)
        validateAddBrandDialogName()
    }

    private fun validateAddBrandDialogName() {
        val isValid = uiState.addBrandDialogName.isNotBlank()
        uiState = uiState.copy(isValidAddBrandDialogName = isValid)
    }

    fun onAddBrandDialogImageUrlChange(text: String) {
        uiState = uiState.copy(addBrandDialogImageUrl = text)
        validateAddBrandDialogImageUrl()
    }

    private fun validateAddBrandDialogImageUrl() {
        val isValid = Patterns.WEB_URL.matcher(uiState.addBrandDialogImageUrl).matches()
        uiState = uiState.copy(isValidAddBrandDialogImageUrl = isValid)
    }

    private fun getAllBrands() {
        viewModelScope.launch {
            getAllBrandsUseCase()
                .catch { cause ->
                    uiState = uiState.copy(errorMessageId = (cause as Exception).getMessageId())
                }
                .collect { brands ->
                    uiState = uiState.copy(brands = brands)
                }
        }
    }

    fun addBrand() {
        viewModelScope.launch {
            try {
                addBrandUseCase(uiState.addBrandDialogName, uiState.addBrandDialogImageUrl)
                resetAddBrandDialog()
            } catch (e: Exception) {
                uiState = uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }

    fun deleteBrand(brandId: String) {
        viewModelScope.launch {
            try {
                deleteBrandUseCase(brandId)
            } catch (e: Exception) {
                uiState = uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }
}

data class BrandsUiState(
    val errorMessageId: Int? = null,
    val showAddBrandDialog: Boolean = false,
    val addBrandDialogName: String = "",
    val isValidAddBrandDialogName: Boolean = false,
    val addBrandDialogImageUrl: String = "",
    val isValidAddBrandDialogImageUrl: Boolean = false,
    val brands: List<Brand> = emptyList()
)