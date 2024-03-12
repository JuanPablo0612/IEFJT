package com.iefjt.android.data.elements.model

import com.iefjt.android.domain.model.Element

data class ElementModel(
    val id: String,
    val name: String,
    val typeId: String,
    val brandId: String,
    val serial: String,
    val statusId: String,
    val headquartersId: String,
    val observations: String
) {
    constructor() : this(
        id = "",
        name = "",
        typeId = "",
        brandId = "",
        serial = "",
        statusId = "",
        headquartersId = "",
        observations = ""
    )
}

fun ElementModel.toDomain() = Element(
    id = id,
    name = name,
    typeId = typeId,
    brandId = brandId,
    serial = serial,
    statusId = statusId,
    headquartersId = headquartersId,
    observations = observations
)
