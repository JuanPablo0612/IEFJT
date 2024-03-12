package com.iefjt.android.domain.usecase.elements

import com.iefjt.android.data.elements.ElementsRepository
import com.iefjt.android.domain.model.Element
import javax.inject.Inject

class AddElementUseCase @Inject constructor(private val elementsRepository: ElementsRepository) {
    suspend operator fun invoke(
        name: String,
        typeId: String,
        brandId: String,
        serial: String,
        statusId: String,
        headquartersId: String,
        observations: String
    ) {
        val element = Element(
            name = name,
            typeId = typeId,
            brandId = brandId,
            serial = serial,
            statusId = statusId,
            headquartersId = headquartersId,
            observations = observations
        )

        elementsRepository.add(element)
    }
}