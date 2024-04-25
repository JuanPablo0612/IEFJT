package com.iefjt.android.ui.common.buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun ScaffoldAddButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}