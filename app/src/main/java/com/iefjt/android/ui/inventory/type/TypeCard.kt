package com.iefjt.android.ui.inventory.type

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.iefjt.android.domain.model.Type

@Composable
fun TypeCard(type: Type, onClick: () -> Unit, onDelete: () -> Unit) {
    Card(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(10.dp)) {
            AsyncImage(
                model = type.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .width(200.dp)
                    .height(100.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = type.name, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(5.dp))

            IconButton(onClick = onDelete, modifier = Modifier.align(Alignment.End)) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}