package com.iefjt.android.data.types.model

import com.iefjt.android.domain.model.Type

data class TypeModel(
    val id: String,
    val name: String,
    val imageUrl: String
) {
    constructor() : this(id = "", name = "", imageUrl = "")
}

fun TypeModel.toDomain() = Type(id = id, name = name, imageUrl = imageUrl)
