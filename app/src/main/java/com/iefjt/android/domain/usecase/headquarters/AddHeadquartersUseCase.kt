package com.iefjt.android.domain.usecase.headquarters

import com.iefjt.android.data.headquarters.HeadquartersRepository
import com.iefjt.android.domain.model.Headquarters
import javax.inject.Inject

class AddHeadquartersUseCase @Inject constructor(private val headquartersRepository: HeadquartersRepository) {
    suspend operator fun invoke(name: String) {
        val headquarters = Headquarters(name = name)
        headquartersRepository.add(headquarters)
    }
}