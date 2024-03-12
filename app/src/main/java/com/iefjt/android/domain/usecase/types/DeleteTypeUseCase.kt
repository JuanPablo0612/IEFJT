package com.iefjt.android.domain.usecase.types

import com.iefjt.android.data.types.TypesRepository
import javax.inject.Inject

class DeleteTypeUseCase @Inject constructor(private val typesRepository: TypesRepository) {
    suspend operator fun invoke(typeId: String) = typesRepository.delete(typeId)
}