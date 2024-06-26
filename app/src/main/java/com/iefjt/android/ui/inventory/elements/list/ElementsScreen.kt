package com.iefjt.android.ui.inventory.elements.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iefjt.android.R
import com.iefjt.android.ui.common.CircularLoading
import com.iefjt.android.ui.common.ScaffoldContent
import com.iefjt.android.ui.common.buttons.ScaffoldAddButton
import com.iefjt.android.ui.inventory.common.BrandCard
import com.iefjt.android.ui.inventory.common.ElementCard
import com.iefjt.android.ui.inventory.common.HeadquartersCard
import com.iefjt.android.ui.inventory.common.SelectCard
import com.iefjt.android.ui.inventory.common.StatusCard
import com.iefjt.android.ui.inventory.common.TypeCard
import com.iefjt.android.ui.inventory.elements.common.brandSelectorIntent
import com.iefjt.android.ui.inventory.elements.common.brandSelectorLauncher
import com.iefjt.android.ui.inventory.elements.common.headquartersSelectorIntent
import com.iefjt.android.ui.inventory.elements.common.headquartersSelectorLauncher
import com.iefjt.android.ui.inventory.elements.common.statusSelectorIntent
import com.iefjt.android.ui.inventory.elements.common.statusSelectorLauncher
import com.iefjt.android.ui.inventory.elements.common.typeSelectorIntent
import com.iefjt.android.ui.inventory.elements.common.typeSelectorLauncher
import com.iefjt.android.ui.navigation.Screen

@Composable
fun ElementsScreen(viewModel: ElementsViewModel = hiltViewModel(), navController: NavController) {
    val uiState = viewModel.uiState
    val context = LocalContext.current

    val brandSelectorLauncher =
        brandSelectorLauncher(
            onSelect = { viewModel.onBrandFilterChange(it) },
            onCancel = {}
        )

    val headquartersSelectorLauncher =
        headquartersSelectorLauncher(
            onSelect = { viewModel.onHeadquartersFilterChange(it) },
            onCancel = {}
        )

    val typeSelectorLauncher =
        typeSelectorLauncher(
            onSelect = { viewModel.onTypeFilterChange(it) },
            onCancel = {}
        )

    val statusSelectorLauncher =
        statusSelectorLauncher(
            onSelect = { viewModel.onStatusFilterChange(it) },
            onCancel = {}
        )

    Scaffold(
        topBar = {
            ElementsTopBar()
        },
        floatingActionButton = {
            ScaffoldAddButton(onClick = { navController.navigate(Screen.AddElement.route) })
        }
    ) { padding ->
        ScaffoldContent(padding = padding) {
            if (uiState.loading) {
                CircularLoading()
            } else {
                if (uiState.elements.isEmpty()) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = stringResource(id = R.string.elements_empty),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                } else {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        OutlinedButton(
                            onClick = viewModel::onFiltersDialogVisibilityChange,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = stringResource(id = R.string.elements_filter))
                        }

                        if (uiState.headquartersFilterVisible) {
                            Text(
                                text = stringResource(id = R.string.element_headquarters),
                                style = MaterialTheme.typography.labelLarge
                            )

                            if (uiState.headquarters != null) {
                                HeadquartersCard(
                                    headquarters = uiState.headquarters,
                                    onClick = {
                                        headquartersSelectorLauncher.launch(
                                            headquartersSelectorIntent(context)
                                        )
                                    }
                                )
                            } else {
                                SelectCard(onClick = {
                                    headquartersSelectorLauncher.launch(
                                        headquartersSelectorIntent(context)
                                    )
                                })
                            }
                        }

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (uiState.brandFilterVisible) {
                                item {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(
                                            text = stringResource(id = R.string.element_brand),
                                            style = MaterialTheme.typography.labelLarge
                                        )

                                        if (uiState.brand != null) {
                                            BrandCard(
                                                brand = uiState.brand,
                                                onClick = {
                                                    brandSelectorLauncher.launch(
                                                        brandSelectorIntent(context)
                                                    )
                                                }
                                            )
                                        } else {
                                            SelectCard(onClick = {
                                                brandSelectorLauncher.launch(
                                                    brandSelectorIntent(context)
                                                )
                                            })
                                        }
                                    }
                                }
                            }

                            if (uiState.typeFilterVisible) {
                                item {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(
                                            text = stringResource(id = R.string.element_type),
                                            style = MaterialTheme.typography.labelLarge
                                        )

                                        if (uiState.type != null) {
                                            TypeCard(
                                                type = uiState.type,
                                                onClick = {
                                                    typeSelectorLauncher.launch(
                                                        typeSelectorIntent(context)
                                                    )
                                                }
                                            )
                                        } else {
                                            SelectCard(onClick = {
                                                typeSelectorLauncher.launch(
                                                    typeSelectorIntent(context)
                                                )
                                            })
                                        }
                                    }
                                }
                            }

                            if (uiState.statusFilterVisible) {
                                item {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(
                                            text = stringResource(id = R.string.element_status),
                                            style = MaterialTheme.typography.labelLarge
                                        )

                                        if (uiState.status != null) {
                                            StatusCard(status = uiState.status, onClick = {
                                                statusSelectorLauncher.launch(
                                                    statusSelectorIntent(context)
                                                )
                                            })
                                        } else {
                                            SelectCard(onClick = {
                                                statusSelectorLauncher.launch(
                                                    statusSelectorIntent(context)
                                                )
                                            })
                                        }
                                    }
                                }
                            }
                        }

                        val isFiltered =
                            uiState.brandFilterVisible || uiState.headquartersFilterVisible || uiState.statusFilterVisible || uiState.typeFilterVisible
                        val filteredElements = uiState.elements
                            .filter { element ->
                                (uiState.brandFilterVisible && element.brand.id == uiState.brand?.id) ||
                                        (uiState.headquartersFilterVisible && element.headquarters.id == uiState.headquarters?.id) ||
                                        (uiState.statusFilterVisible && element.status.id == uiState.status?.id) ||
                                        (uiState.typeFilterVisible && element.type.id == uiState.type?.id)
                            }

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp)
                        ) {
                            items(
                                if (isFiltered) filteredElements else uiState.elements,
                                key = { it.id }) { element ->
                                ElementCard(
                                    element = element,
                                    onClick = {
                                        navController.navigate(route = "${Screen.ElementDetails.route}/${element.id}")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (uiState.filtersDialogVisible) {
        SelectFiltersDialog(
            currentBrandFilterVisible = uiState.brandFilterVisible,
            currentHeadquartersFilterVisible = uiState.headquartersFilterVisible,
            currentStatusFilterVisible = uiState.statusFilterVisible,
            currentTypeFilterVisible = uiState.typeFilterVisible,
            onFiltersVisibilityChange = viewModel::onFiltersVisibilityChange,
            onDialogVisibilityChange = viewModel::onFiltersDialogVisibilityChange
        )
    }
}