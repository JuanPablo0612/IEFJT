package com.iefjt.android.ui.inventory.statuses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.iefjt.android.domain.model.Status

@Composable
fun StatusCard(status: Status, onClick: () -> Unit, onDelete: () -> Unit) {
    Card(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(10.dp)) {
            val color = status.color.replace("#", "").toLong(radix = 16)

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = status.name,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color(color))
                            .clip(CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            IconButton(onClick = onDelete, modifier = Modifier.align(Alignment.End)) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}