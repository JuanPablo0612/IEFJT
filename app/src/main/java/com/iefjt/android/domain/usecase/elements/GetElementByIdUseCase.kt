package com.iefjt.android.domain.usecase.elements

import com.iefjt.android.data.elements.ElementsRepository
import javax.inject.Inject

class GetElementByIdUseCase @Inject constructor(private val elementsRepository: ElementsRepository) {
    suspend operator fun invoke(elementId: String) = elementsRepository.getById(elementId)
}