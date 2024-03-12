package com.iefjt.android.domain.usecase.headquarters

import com.iefjt.android.data.headquarters.HeadquartersRepository
import javax.inject.Inject

class GetAllHeadquartersUseCase @Inject constructor(private val headquartersRepository: HeadquartersRepository) {
    operator fun invoke() = headquartersRepository.allHeadquarters
}