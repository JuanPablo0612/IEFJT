package com.iefjt.android.ui.inventory.elements.details

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iefjt.android.R
import com.iefjt.android.ui.common.ScaffoldContent
import com.iefjt.android.ui.inventory.elements.common.ElementBrandCard
import com.iefjt.android.ui.inventory.elements.common.ElementStatusCard
import com.iefjt.android.ui.inventory.elements.common.ElementTypeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElementDetailsScreen(
    viewModel: ElementDetailsViewModel = hiltViewModel(),
    elementId: String,
    navController: NavController
) {
    val uiState = viewModel.uiState

    LaunchedEffect(key1 = elementId) {
        viewModel.init(elementId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(id = R.string.element_details_title)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.deleteElement() }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
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
                if (uiState.element != null) {
                    Text(
                        text = stringResource(id = R.string.element_name),
                        style = MaterialTheme.typography.labelLarge
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(text = uiState.element.name, style = MaterialTheme.typography.titleLarge)

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = stringResource(id = R.string.element_serial),
                        style = MaterialTheme.typography.labelLarge
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = uiState.element.serial,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = stringResource(id = R.string.element_headquarters),
                        style = MaterialTheme.typography.labelLarge
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = uiState.element.headquarters.name,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        item {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = stringResource(id = R.string.element_brand),
                                    style = MaterialTheme.typography.labelLarge
                                )

                                Spacer(modifier = Modifier.height(5.dp))

                                ElementBrandCard(brand = uiState.element.brand)
                            }
                        }

                        item {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = stringResource(id = R.string.element_type),
                                    style = MaterialTheme.typography.labelLarge
                                )

                                Spacer(modifier = Modifier.height(5.dp))

                                ElementTypeCard(type = uiState.element.type)
                            }
                        }

                        item {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = stringResource(id = R.string.element_status),
                                    style = MaterialTheme.typography.labelLarge
                                )

                                Spacer(modifier = Modifier.height(5.dp))

                                ElementStatusCard(status = uiState.element.status)
                            }
                        }
                    }
                }
            }
        }
    }
}