package com.iefjt.android.ui.inventory.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.iefjt.android.R
import com.iefjt.android.domain.model.Brand
import com.iefjt.android.domain.model.ElementWithAllData
import com.iefjt.android.domain.model.Headquarters
import com.iefjt.android.domain.model.Status
import com.iefjt.android.domain.model.Type

@Composable
fun SelectCard(onClick: () -> Unit = {}) {
    Card(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.select),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
private fun TextCard(
    id: String,
    text: String,
    showDeleteButton: Boolean,
    onClick: (id: String) -> Unit = {},
    onDelete: (id: String) -> Unit
) {
    Card(onClick = { onClick(id) }, modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )

            if (showDeleteButton) {
                IconButton(onClick = { onDelete(id) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
private fun BaseVerticalCard(
    id: String,
    imageUrl: String,
    text: String,
    showDeleteButton: Boolean,
    onClick: (id: String) -> Unit,
    onDelete: (id: String) -> Unit
) {
    Card(onClick = { onClick(id) }, modifier = Modifier.fillMaxWidth()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .width(200.dp)
                    .height(100.dp)
            )

            Text(text = text, style = MaterialTheme.typography.bodyLarge)

            if (showDeleteButton) {
                Button(
                    onClick = { onDelete(id) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.delete)
                    )
                }
            }
        }
    }
}

@Composable
private fun BaseHorizontalCard(
    id: String,
    imageUrl: String,
    text: String,
    showDeleteButton: Boolean,
    onClick: (id: String) -> Unit,
    onDelete: (id: String) -> Unit
) {
    Card(onClick = { onClick(id) }, modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.size(width = 100.dp, height = 50.dp)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(0.6f)
            )

            if (showDeleteButton) {
                IconButton(onClick = { onDelete(id) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
private fun BaseHorizontalColorCard(
    id: String,
    color: Color,
    text: String,
    showDeleteButton: Boolean,
    onClick: (id: String) -> Unit,
    onDelete: (id: String) -> Unit
) {
    Card(onClick = { onClick(id) }, modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(color)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )

            if (showDeleteButton) {
                IconButton(onClick = { onDelete(id) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
fun BrandCard(
    brand: Brand,
    allowActions: Boolean = false,
    onClick: (brandId: String) -> Unit = {},
    onDelete: (brandId: String) -> Unit = {}
) {
    BaseVerticalCard(
        id = brand.id,
        showDeleteButton = allowActions,
        imageUrl = brand.imageUrl,
        text = brand.name,
        onClick = onClick,
        onDelete = onDelete
    )
}

@Composable
fun TypeCard(
    type: Type,
    allowActions: Boolean = false,
    onClick: (typeId: String) -> Unit = {},
    onDelete: (typeId: String) -> Unit = {}
) {
    BaseVerticalCard(
        id = type.id,
        imageUrl = type.imageUrl,
        text = type.name,
        showDeleteButton = allowActions,
        onClick = onClick,
        onDelete = onDelete
    )
}

@Composable
fun ElementCard(
    element: ElementWithAllData,
    allowActions: Boolean = false,
    onClick: (elementId: String) -> Unit = {},
    onDelete: (elementId: String) -> Unit = {}
) {
    BaseHorizontalCard(
        id = element.id,
        imageUrl = element.type.imageUrl,
        text = element.name,
        showDeleteButton = allowActions,
        onClick = onClick,
        onDelete = onDelete
    )
}

@Composable
fun HeadquartersCard(
    headquarters: Headquarters,
    allowActions: Boolean = false,
    onClick: (headquartersId: String) -> Unit = {},
    onDelete: (headquartersId: String) -> Unit = {}
) {
    TextCard(
        id = headquarters.id,
        text = headquarters.name,
        showDeleteButton = allowActions,
        onClick = onClick,
        onDelete = onDelete
    )
}

@Composable
fun StatusCard(
    status: Status,
    allowActions: Boolean = false,
    onClick: (statusId: String) -> Unit = {},
    onDelete: (statusId: String) -> Unit = {}
) {
    val colorString = status.color.removePrefix("#")

    val colorLong = colorString.toLong(16)

    val color = if (colorString.length == 6) {
        Color(colorLong or 0xFF000000)
    } else {
        Color(colorLong)
    }

    BaseHorizontalColorCard(
        id = status.id,
        color = color,
        text = status.name,
        showDeleteButton = allowActions,
        onClick = onClick,
        onDelete = onDelete
    )
}