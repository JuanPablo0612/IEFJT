package com.iefjt.android.data.statuses.model

import com.iefjt.android.domain.model.Status

data class StatusModel(
    val id: String,
    val name: String,
    val color: String
) {
    constructor() : this(id = "", name = "", color = "")
}

fun StatusModel.toDomain() = Status(id = id, name = name, color = color)
