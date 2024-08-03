package com.iefjt.android.data.elements.model

import com.iefjt.android.domain.model.Element

data class ElementModel(
    val id: String,
    val name: String,
    val type: String,
    val brand: String,
    val serial: String,
    val status: String,
    val headquarters: String,
    val observations: String
) {
    constructor() : this(
        id = "",
        name = "",
        type = "",
        brand = "",
        serial = "",
        status = "",
        headquarters = "",
        observations = ""
    )
}

fun ElementModel.toDomain() = Element(
    id = id,
    name = name,
    type = type,
    brand = brand,
    serial = serial,
    status = status,
    headquarters = headquarters,
    observations = observations
)
