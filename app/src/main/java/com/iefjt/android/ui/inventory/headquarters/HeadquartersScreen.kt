package com.iefjt.android.ui.inventory.headquarters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun HeadquartersScreen(
    viewModel: HeadquartersViewModel = hiltViewModel(),
    onSelect: (headquartersId: String) -> Unit,
    onCancel: () -> Unit
) {
    val uiState = viewModel.uiState

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(id = R.string.headquarters_title)) },
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
            FloatingActionButton(onClick = { viewModel.onShowAddHeadquartersDialogChange() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->
        ScaffoldContent(padding = padding) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                items(uiState.headquarters, key = { it.id }) { headquarters ->
                    HeadquartersCard(
                        headquarters = headquarters,
                        onClick = {
                            onSelect(headquarters.id)
                        },
                        onDelete = { viewModel.deleteHeadquarters(headquarters.id) }
                    )
                }
            }
        }
    }

    if (uiState.showAddHeadquartersDialog) {
        AddHeadquartersDialog(
            name = uiState.addHeadquartersDialogName,
            isValidName = uiState.isValidAddHeadquartersDialogName,
            onNameChange = { viewModel.onAddHeadquartersDialogNameChange(it) },
            onConfirm = { viewModel.addHeadquarters() },
            onDismiss = { viewModel.onShowAddHeadquartersDialogChange() }
        )
    }
}