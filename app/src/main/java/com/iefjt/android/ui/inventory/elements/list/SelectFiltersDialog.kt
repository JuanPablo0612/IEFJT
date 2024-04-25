package com.iefjt.android.ui.inventory.elements.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iefjt.android.R

@Composable
fun SelectFiltersDialog(
    currentBrandFilterVisible: Boolean,
    currentHeadquartersFilterVisible: Boolean,
    currentStatusFilterVisible: Boolean,
    currentTypeFilterVisible: Boolean,
    onFiltersVisibilityChange: (Boolean, Boolean, Boolean, Boolean) -> Unit,
    onDialogVisibilityChange: () -> Unit
) {
    var brandFilterVisible by rememberSaveable { mutableStateOf(currentBrandFilterVisible) }
    var headquartersFilterVisible by rememberSaveable {
        mutableStateOf(currentHeadquartersFilterVisible)
    }
    var statusFilterVisible by rememberSaveable { mutableStateOf(currentStatusFilterVisible) }
    var typeFilterVisible by rememberSaveable { mutableStateOf(currentTypeFilterVisible) }
    var allFiltersVisible by rememberSaveable { mutableStateOf(false) }

    allFiltersVisible =
        brandFilterVisible && headquartersFilterVisible && statusFilterVisible && typeFilterVisible

    AlertDialog(
        onDismissRequest = onDialogVisibilityChange,
        confirmButton = {
            Button(onClick = {
                onDialogVisibilityChange()
                onFiltersVisibilityChange(
                    brandFilterVisible,
                    headquartersFilterVisible,
                    statusFilterVisible,
                    typeFilterVisible
                )
            }) {
                Text(text = stringResource(id = R.string.confirm))
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDialogVisibilityChange) {
                Text(text = stringResource(id = R.string.cancel))
            }
        },
        title = {
            Text(text = stringResource(id = R.string.elements_filter))
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                FilterCheckBox(
                    checked = allFiltersVisible,
                    text = stringResource(id = R.string.elements_filter_all),
                    onCheckedChange = {
                        allFiltersVisible = it
                        brandFilterVisible = it
                        headquartersFilterVisible = it
                        statusFilterVisible = it
                        typeFilterVisible = it
                    }
                )

                FilterCheckBox(
                    checked = brandFilterVisible,
                    text = stringResource(id = R.string.elements_filter_brand),
                    onCheckedChange = { brandFilterVisible = it }
                )

                FilterCheckBox(
                    checked = headquartersFilterVisible,
                    text = stringResource(id = R.string.elements_filter_headquarters),
                    onCheckedChange = { headquartersFilterVisible = it }
                )

                FilterCheckBox(
                    checked = statusFilterVisible,
                    text = stringResource(id = R.string.elements_filter_status),
                    onCheckedChange = { statusFilterVisible = it }
                )

                FilterCheckBox(
                    checked = typeFilterVisible,
                    text = stringResource(id = R.string.elements_filter_type),
                    onCheckedChange = { typeFilterVisible = it }
                )
            }
        }
    )
}

@Composable
fun FilterCheckBox(checked: Boolean, text: String, onCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(text = text)
    }
}