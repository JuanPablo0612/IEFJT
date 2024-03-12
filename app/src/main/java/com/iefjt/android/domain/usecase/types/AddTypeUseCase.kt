package com.iefjt.android.domain.usecase.types

import com.iefjt.android.data.types.TypesRepository
import com.iefjt.android.domain.model.Type
import javax.inject.Inject

class AddTypeUseCase @Inject constructor(private val typesRepository: TypesRepository) {
    suspend operator fun invoke(name: String, imageUrl: String) {
        val type = Type(name = name, imageUrl = imageUrl)
        typesRepository.add(type)
    }
}