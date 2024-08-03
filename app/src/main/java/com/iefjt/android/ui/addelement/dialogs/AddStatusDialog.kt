package com.iefjt.android.ui.addelement.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.iefjt.android.R

@Composable
fun AddStatusDialog(
    onConfirm: (name: String, color: String) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    val isValidName = name.isNotBlank()
    val isValidColor = color.length == 6 && color.matches(Regex("[0-9A-Fa-f]+"))

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = { onConfirm(name, color) }, enabled = isValidName && isValidColor) {
                Text(text = stringResource(id = R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.cancel))
            }
        },
        title = {
            Text(text = stringResource(id = R.string.statuses_add_status))
        },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = {
                        Text(text = stringResource(id = R.string.statuses_add_name))
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Title, contentDescription = null)
                    },
                    isError = !isValidName,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = color,
                    onValueChange = { color = it },
                    label = {
                        Text(text = stringResource(id = R.string.statuses_add_color))
                    },
                    leadingIcon = {
                        val iconTint = if (isValidColor) {
                            val colorLong = color.replace("#", "").toLong(radix = 16)
                            Color(colorLong)
                        } else LocalContentColor.current

                        Icon(
                            imageVector = Icons.Default.ColorLens,
                            contentDescription = null,
                            tint = iconTint
                        )
                    },
                    isError = !isValidColor,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}