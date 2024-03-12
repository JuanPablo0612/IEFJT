package com.iefjt.android.ui.inventory.statuses

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.iefjt.android.R

@Composable
fun AddStatusDialog(
    name: String,
    isValidName: Boolean,
    onNameChange: (text: String) -> Unit,
    color: String,
    isValidColor: Boolean,
    onColorChange: (text: String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onConfirm, enabled = isValidName && isValidColor) {
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
                    onValueChange = onNameChange,
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
                    onValueChange = onColorChange,
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