package com.iefjt.android.domain.usecase.elements

import com.iefjt.android.data.elements.ElementsRepository
import com.iefjt.android.domain.model.Element
import javax.inject.Inject

class AddElementUseCase @Inject constructor(private val elementsRepository: ElementsRepository) {
    suspend operator fun invoke(
        name: String,
        type: String,
        brand: String,
        serial: String,
        status: String,
        headquarters: String,
        observations: String
    ): Result<Nothing?> {
        val element = Element(
            name = name,
            type = type,
            brand = brand,
            serial = serial,
            status = status,
            headquarters = headquarters,
            observations = observations
        )

        return elementsRepository.add(element)
    }
}