package com.iefjt.android.data.brands.model

import com.iefjt.android.domain.model.Brand

data class BrandModel(
    val id: String,
    val name: String,
    val imageUrl: String
) {
    constructor() : this(id = "", name = "", imageUrl = "")
}

fun BrandModel.toDomain() = Brand(id = id, name = name, imageUrl = imageUrl)
