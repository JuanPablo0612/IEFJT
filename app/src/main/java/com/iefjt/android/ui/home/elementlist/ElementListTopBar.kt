package com.iefjt.android.ui.home.elementlist

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.iefjt.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElementListTopBar() {
    TopAppBar(title = { Text(text = stringResource(id = R.string.inventory_items)) })
}