package com.iefjt.android.ui.inventory.brands

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iefjt.android.domain.model.Brand
import com.iefjt.android.ui.inventory.common.BrandCard

@Composable
fun BrandList(
    brands: List<Brand>,
    onSelect: (brandId: String) -> Unit,
    onDelete: (brandId: String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        items(brands, key = { it.id }) { brand ->
            BrandCard(
                brand = brand,
                allowActions = true,
                onClick = onSelect,
                onDelete = onDelete
            )
        }
    }
}