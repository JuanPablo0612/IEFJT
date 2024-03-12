package com.iefjt.android.domain.model

import com.iefjt.android.data.headquarters.model.HeadquartersModel

data class Headquarters(
    val id: String = "",
    val name: String = ""
)

fun Headquarters.toModel() = HeadquartersModel(id = id, name = name)
