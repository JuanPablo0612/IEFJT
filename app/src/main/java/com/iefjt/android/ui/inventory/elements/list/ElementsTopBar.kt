package com.iefjt.android.ui.inventory.elements.list

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.iefjt.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElementsTopBar() {
    TopAppBar(title = { Text(text = stringResource(id = R.string.elements_title)) })
}