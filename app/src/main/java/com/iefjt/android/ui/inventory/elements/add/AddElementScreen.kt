package com.iefjt.android.ui.inventory.elements.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iefjt.android.R
import com.iefjt.android.ui.common.ScaffoldContent
import com.iefjt.android.ui.inventory.common.BrandCard
import com.iefjt.android.ui.inventory.elements.common.ElementBrand
import com.iefjt.android.ui.inventory.elements.common.ElementStatus
import com.iefjt.android.ui.inventory.elements.common.ElementType
import com.iefjt.android.ui.inventory.elements.common.EmptySelectionCard
import com.iefjt.android.ui.inventory.elements.common.brandSelectorIntent
import com.iefjt.android.ui.inventory.elements.common.brandSelectorLauncher
import com.iefjt.android.ui.inventory.elements.common.headquartersSelectorIntent
import com.iefjt.android.ui.inventory.elements.common.headquartersSelectorLauncher
import com.iefjt.android.ui.inventory.elements.common.statusSelectorIntent
import com.iefjt.android.ui.inventory.elements.common.statusSelectorLauncher
import com.iefjt.android.ui.inventory.elements.common.typeSelectorIntent
import com.iefjt.android.ui.inventory.elements.common.typeSelectorLauncher

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddElementScreen(
    viewModel: AddElementViewModel = hiltViewModel(),
    navController: NavController
) {
    val uiState = viewModel.uiState
    val context = LocalContext.current

    val brandSelectorLauncher =
        brandSelectorLauncher(
            onSelect = { viewModel.onBrandSelect(it) },
            onCancel = {}
        )

    val headquartersSelectorLauncher =
        headquartersSelectorLauncher(
            onSelect = { viewModel.onHeadquartersSelect(it) },
            onCancel = {}
        )

    val typeSelectorLauncher =
        typeSelectorLauncher(
            onSelect = { viewModel.onTypeSelect(it) },
            onCancel = {}
        )

    val statusSelectorLauncher =
        statusSelectorLauncher(
            onSelect = { viewModel.onStatusSelect(it) },
            onCancel = {}
        )

    LaunchedEffect(key1 = uiState.successful) {
        if (uiState.successful) {
            navController.navigateUp()
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(id = R.string.element_add_title)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { padding ->
        ScaffoldContent(padding = padding) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.element_name),
                    style = MaterialTheme.typography.labelLarge
                )

                Spacer(modifier = Modifier.height(5.dp))

                OutlinedTextField(
                    value = uiState.name,
                    onValueChange = { viewModel.onNameChange(it) },
                    label = {
                        Text(text = stringResource(id = R.string.element_name))
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Title, contentDescription = null)
                    },
                    isError = !uiState.isValidName,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = stringResource(id = R.string.element_serial),
                    style = MaterialTheme.typography.labelLarge
                )

                Spacer(modifier = Modifier.height(5.dp))

                OutlinedTextField(
                    value = uiState.serial,
                    onValueChange = { viewModel.onSerialChange(it) },
                    label = {
                        Text(text = stringResource(id = R.string.element_serial))
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Numbers, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = stringResource(id = R.string.element_headquarters),
                    style = MaterialTheme.typography.labelLarge
                )

                Spacer(modifier = Modifier.height(5.dp))

                OutlinedCard(
                    onClick = {
                        headquartersSelectorLauncher.launch(headquartersSelectorIntent(context))
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        if (uiState.headquarters != null) {
                            Text(
                                text = uiState.headquarters.name,
                                style = MaterialTheme.typography.titleLarge
                            )
                        } else {
                            Text(
                                text = stringResource(id = R.string.element_add_select),
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    item {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = stringResource(id = R.string.element_brand),
                                style = MaterialTheme.typography.labelLarge
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            OutlinedCard(
                                onClick = {
                                    brandSelectorLauncher.launch(brandSelectorIntent(context))
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                if (uiState.brand != null) {
                                    BrandCard(brand = uiState.brand)
                                } else {
                                    EmptySelectionCard()
                                }
                            }
                        }
                    }

                    item {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = stringResource(id = R.string.element_type),
                                style = MaterialTheme.typography.labelLarge
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            OutlinedCard(
                                onClick = { typeSelectorLauncher.launch(typeSelectorIntent(context)) },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                if (uiState.type != null) {
                                    ElementType(type = uiState.type)
                                } else {
                                    EmptySelectionCard()
                                }
                            }
                        }
                    }

                    item {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = stringResource(id = R.string.element_status),
                                style = MaterialTheme.typography.labelLarge
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            OutlinedCard(
                                onClick = { statusSelectorLauncher.launch(statusSelectorIntent(context)) },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                if (uiState.status != null) {
                                    ElementStatus(status = uiState.status)
                                } else {
                                    EmptySelectionCard()
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                val enableButton =
                    uiState.isValidName && uiState.brand != null && uiState.headquarters != null && uiState.type != null && uiState.status != null

                Button(
                    onClick = { viewModel.addElement() },
                    enabled = enableButton,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.element_add_save))
                }
            }
        }
    }
}