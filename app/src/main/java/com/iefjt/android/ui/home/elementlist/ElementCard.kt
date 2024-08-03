package com.iefjt.android.ui.home.elementlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iefjt.android.R
import com.iefjt.android.domain.model.Element

@Composable
fun ElementCard(element: Element, onEdit: (id: String) -> Unit, onDelete: (id: String) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = element.name, style = MaterialTheme.typography.titleLarge)

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text(
                            text = stringResource(id = R.string.element_headquarters),
                            style = MaterialTheme.typography.labelLarge
                        )
                        Text(
                            text = element.headquarters,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                item {
                    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text(
                            text = stringResource(id = R.string.element_type),
                            style = MaterialTheme.typography.labelLarge
                        )
                        Text(text = element.type, style = MaterialTheme.typography.bodyLarge)
                    }
                }

                item {
                    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text(
                            text = stringResource(id = R.string.element_brand),
                            style = MaterialTheme.typography.labelLarge
                        )
                        Text(text = element.brand, style = MaterialTheme.typography.bodyLarge)
                    }
                }

                item {
                    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text(
                            text = stringResource(id = R.string.element_status),
                            style = MaterialTheme.typography.labelLarge
                        )
                        Text(text = element.status, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { onEdit(element.id) }, modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(id = R.string.element_edit))
                }
                Button(
                    onClick = { onDelete(element.id) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = stringResource(id = R.string.element_delete))
                }
            }
        }
    }
}