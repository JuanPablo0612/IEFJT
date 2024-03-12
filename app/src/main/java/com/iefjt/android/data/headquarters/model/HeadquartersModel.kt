package com.iefjt.android.data.headquarters.model

import com.iefjt.android.domain.model.Headquarters

data class HeadquartersModel(
    val id: String,
    val name: String
) {
    constructor() : this(id = "", name = "")
}

fun HeadquartersModel.toDomain() = Headquarters(id = id, name = name)
