package com.iefjt.android.ui.inventory.elements.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.iefjt.android.R
import com.iefjt.android.domain.model.Type

@Composable
fun ElementTypeCard(type: Type) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = type.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(text = type.name, style = MaterialTheme.typography.bodyLarge)
    }
}