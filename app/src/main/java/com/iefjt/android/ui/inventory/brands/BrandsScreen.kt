package com.iefjt.android.ui.inventory.brands

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.iefjt.android.ui.common.buttons.ScaffoldAddButton
import com.iefjt.android.ui.common.ScaffoldContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandsScreen(
    viewModel: BrandsViewModel = hiltViewModel(),
    onSelect: (brandId: String) -> Unit,
    onCancel: () -> Unit
) {
    val uiState = viewModel.uiState

    Scaffold(
        topBar = { BrandsTopBar(onCancel = onCancel) },
        floatingActionButton = {
            ScaffoldAddButton(onClick = viewModel::onShowAddBrandDialogChange)
        }
    ) { padding ->
        ScaffoldContent(padding = padding) {
            BrandList(
                brands = uiState.brands,
                onSelect = onSelect,
                onDelete = viewModel::deleteBrand
            )
        }
    }

    if (uiState.showAddBrandDialog) {
        AddBrandDialog(
            name = uiState.addBrandDialogName,
            isValidName = uiState.isValidAddBrandDialogName,
            imageUrl = uiState.addBrandDialogImageUrl,
            isValidImageUrl = uiState.isValidAddBrandDialogImageUrl,
            onNameChange = { viewModel.onAddBrandDialogNameChange(it) },
            onImageUrlChange = { viewModel.onAddBrandDialogImageUrlChange(it) },
            onConfirm = { viewModel.addBrand() },
            onDismiss = { viewModel.onShowAddBrandDialogChange() }
        )
    }
}