package com.iefjt.android.domain.usecase.elements

import com.iefjt.android.data.elements.ElementsRepository
import javax.inject.Inject

class GetElementsByTypeUseCase @Inject constructor(private val elementsRepository: ElementsRepository) {
    operator fun invoke(typeId: String) = elementsRepository.getByType(typeId)
}