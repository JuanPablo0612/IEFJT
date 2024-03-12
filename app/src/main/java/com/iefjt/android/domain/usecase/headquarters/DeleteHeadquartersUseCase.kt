package com.iefjt.android.domain.usecase.headquarters

import com.iefjt.android.data.headquarters.HeadquartersRepository
import javax.inject.Inject

class DeleteHeadquartersUseCase @Inject constructor(private val headquartersRepository: HeadquartersRepository) {
    suspend operator fun invoke(headquartersId: String) = headquartersRepository.delete(headquartersId)
}