package com.iefjt.android.domain.model

data class ElementWithAllData(
    val id: String = "",
    val name: String = "",
    val type: Type = Type(),
    val brand: Brand = Brand(),
    val serial: String = "",
    val status: Status = Status(),
    val headquarters: Headquarters = Headquarters(),
    val observations: String = ""
)
