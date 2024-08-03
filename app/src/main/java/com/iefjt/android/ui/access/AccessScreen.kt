package com.iefjt.android.ui.access

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iefjt.android.R
import com.iefjt.android.ui.navigation.Screen

@Composable
fun AccessScreen(viewModel: AccessViewModel = hiltViewModel(), navController: NavController) {
    val uiState = viewModel.uiState

    LaunchedEffect(key1 = uiState.success) {
        if (uiState.success) navController.navigate(route = Screen.Home.route)
    }

    Scaffold(topBar = { AccessTopBar() }) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(innerPadding)
        ) {
            OutlinedTextField(
                value = uiState.email,
                onValueChange = viewModel::onEmailChange,
                modifier = Modifier.fillMaxWidth(),
                enabled = !uiState.loading,
                label = {
                    Text(text = stringResource(id = R.string.email))
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null)
                },
                supportingText = {
                    if (!uiState.isValidEmail) {
                        Text(text = stringResource(id = R.string.invalid_email))
                    }
                },
                isError = !uiState.isValidEmail,
                singleLine = true
            )

            OutlinedTextField(
                value = uiState.password,
                onValueChange = viewModel::onPasswordChange,
                modifier = Modifier.fillMaxWidth(),
                enabled = !uiState.loading,
                label = {
                    Text(text = stringResource(id = R.string.password))
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Password, contentDescription = null)
                },
                trailingIcon = {
                    IconButton(onClick = viewModel::onPasswordVisibilityChange) {
                        val icon =
                            if (uiState.showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility

                        Icon(imageVector = icon, contentDescription = null)
                    }
                },
                supportingText = {
                    if (!uiState.isValidPassword) {
                        Text(text = stringResource(id = R.string.invalid_password))
                    }
                },
                isError = !uiState.isValidPassword,
                visualTransformation = if (uiState.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                singleLine = true
            )

            AnimatedVisibility(visible = !uiState.loading) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = viewModel::onSignIn,
                        modifier = Modifier.weight(1f),
                        enabled = !uiState.loading
                    ) {
                        Text(text = stringResource(id = R.string.sign_in))
                    }

                    OutlinedButton(
                        onClick = viewModel::onSignUp,
                        modifier = Modifier.weight(1f),
                        enabled = !uiState.loading
                    ) {
                        Text(text = stringResource(id = R.string.sign_up))
                    }
                }
            }

            AnimatedVisibility(
                visible = uiState.loading,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            ) {
                CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AccessTopBar() {
    LargeTopAppBar(title = { Text(text = stringResource(id = R.string.access_title)) })
}