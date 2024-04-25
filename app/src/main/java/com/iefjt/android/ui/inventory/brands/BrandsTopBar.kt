package com.iefjt.android.ui.inventory.brands

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.iefjt.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandsTopBar(onCancel: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = R.string.brands_title)) },
        navigationIcon = {
            IconButton(onClick = onCancel) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}