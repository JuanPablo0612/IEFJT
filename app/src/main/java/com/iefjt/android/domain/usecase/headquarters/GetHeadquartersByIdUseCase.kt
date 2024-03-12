package com.iefjt.android.domain.usecase.headquarters

import com.iefjt.android.data.headquarters.HeadquartersRepository
import javax.inject.Inject

class GetHeadquartersByIdUseCase @Inject constructor(private val headquartersRepository: HeadquartersRepository) {
    operator fun invoke(headquarterId: String) = headquartersRepository.getById(headquarterId)
}