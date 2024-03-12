package com.iefjt.android.domain.usecase.types

import com.iefjt.android.data.types.TypesRepository
import javax.inject.Inject

class GetAllTypesUseCase @Inject constructor(private val typesRepository: TypesRepository) {
    operator fun invoke() = typesRepository.allTypes
}