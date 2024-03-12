package com.iefjt.android.domain.model

import com.iefjt.android.data.elements.model.ElementModel

data class Element(
    val id: String = "",
    val name: String = "",
    val typeId: String = "",
    val brandId: String = "",
    val serial: String = "",
    val statusId: String = "",
    val headquartersId: String = "",
    val observations: String = ""
)

fun Element.toModel() = ElementModel(
    id = id,
    name = name,
    typeId = typeId,
    brandId = brandId,
    serial = serial,
    statusId = statusId,
    headquartersId = headquartersId,
    observations = observations
)
