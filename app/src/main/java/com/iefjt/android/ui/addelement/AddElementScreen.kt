package com.iefjt.android.ui.addelement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iefjt.android.R
import com.iefjt.android.ui.common.ScaffoldContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddElementScreen(
    viewModel: AddElementViewModel = hiltViewModel(),
    navController: NavController
) {
    val uiState = viewModel.uiState

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
                OutlinedTextField(
                    value = uiState.name,
                    onValueChange = viewModel::onNameChange,
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

                OutlinedTextField(
                    value = uiState.observations,
                    onValueChange = viewModel::onObservationsChange,
                    label = {
                        Text(text = stringResource(id = R.string.element_observations))
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Description, contentDescription = null)
                    },
                    isError = !uiState.isValidName,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = uiState.serial,
                    onValueChange = viewModel::onSerialChange,
                    label = {
                        Text(text = stringResource(id = R.string.element_serial))
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Numbers, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = uiState.headquarters,
                    onValueChange = viewModel::onHeadquartersChange,
                    label = {
                        Text(text = stringResource(id = R.string.element_headquarters))
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Place, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = uiState.brand,
                    onValueChange = viewModel::onBrandChange,
                    label = {
                        Text(text = stringResource(id = R.string.element_brand))
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Title, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = uiState.type,
                    onValueChange = viewModel::onTypeChange,
                    label = {
                        Text(text = stringResource(id = R.string.element_type))
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Title, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))


                OutlinedTextField(
                    value = uiState.status,
                    onValueChange = viewModel::onStatusChange,
                    label = {
                        Text(text = stringResource(id = R.string.element_status))
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Title, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                val enableButton =
                    uiState.isValidName && uiState.isValidObservations && uiState.isValidSerial && uiState.isValidHeadquarters && uiState.isValidBrand && uiState.isValidType && uiState.isValidStatus

                Button(
                    onClick = viewModel::onAddElement,
                    enabled = enableButton,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.element_add_save))
                }
            }
        }
    }
}