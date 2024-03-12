package com.iefjt.android.domain.model

import com.iefjt.android.data.statuses.model.StatusModel

data class Status(
    val id: String = "",
    val name: String = "",
    val color: String = ""
)

fun Status.toModel() = StatusModel(id = id, name = name, color = color)
