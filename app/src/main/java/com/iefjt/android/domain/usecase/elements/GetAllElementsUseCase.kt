package com.iefjt.android.domain.usecase.elements

import com.iefjt.android.data.elements.ElementsRepository
import javax.inject.Inject

class GetAllElementsUseCase @Inject constructor(private val elementsRepository: ElementsRepository) {
    operator fun invoke() = elementsRepository.getAll()
}