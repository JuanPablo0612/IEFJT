package com.iefjt.android.domain.model

import com.iefjt.android.data.brands.model.BrandModel

data class Brand(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = ""
)

fun Brand.toModel() = BrandModel(id = id, name = name, imageUrl = imageUrl)
