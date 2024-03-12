package com.iefjt.android.ui.inventory.type

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iefjt.android.R
import com.iefjt.android.ui.common.ScaffoldContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypesScreen(
    viewModel: TypesViewModel = hiltViewModel(),
    onSelect: (typeId: String) -> Unit,
    onCancel: () -> Unit
) {
    val uiState = viewModel.uiState

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(id = R.string.types_title)) },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onShowAddTypeDialogChange() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->
        ScaffoldContent(padding = padding) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                items(uiState.types, key = { it.id }) { type ->
                    TypeCard(
                        type = type,
                        onClick = {
                            onSelect(type.id)
                        },
                        onDelete = { viewModel.deleteType(type.id) }
                    )
                }
            }
        }
    }

    if (uiState.showAddTypeDialog) {
        AddTypeDialog(
            name = uiState.addTypeDialogName,
            isValidName = uiState.isValidAddTypeDialogName,
            imageUrl = uiState.addTypeDialogImageUrl,
            isValidImageUrl = uiState.isValidAddTypeDialogImageUrl,
            onNameChange = { viewModel.onAddTypeDialogNameChange(it) },
            onImageUrlChange = { viewModel.onAddTypeDialogImageUrlChange(it) },
            onConfirm = { viewModel.addType() },
            onDismiss = { viewModel.onShowAddTypeDialogChange() }
        )
    }
}