package com.iefjt.android.domain.model

import com.iefjt.android.data.types.model.TypeModel

data class Type(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = ""
)

fun Type.toModel() = TypeModel(id = id, name = name, imageUrl = imageUrl)
