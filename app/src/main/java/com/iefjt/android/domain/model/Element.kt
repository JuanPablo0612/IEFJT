package com.iefjt.android.domain.model

import com.iefjt.android.data.elements.model.ElementModel

data class Element(
    val id: String = "",
    val name: String = "",
    val type: String = "",
    val brand: String = "",
    val serial: String = "",
    val status: String = "",
    val headquarters: String = "",
    val observations: String = ""
)

fun Element.toModel() = ElementModel(
    id = id,
    name = name,
    typeId = type,
    brandId = brand,
    serial = serial,
    statusId = status,
    headquartersId = headquarters,
    observations = observations
)
