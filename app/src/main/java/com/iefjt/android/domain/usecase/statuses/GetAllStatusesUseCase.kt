package com.iefjt.android.domain.usecase.statuses

import com.iefjt.android.data.statuses.StatusesRepository
import javax.inject.Inject

class GetAllStatusesUseCase @Inject constructor(private val statusesRepository: StatusesRepository) {
    operator fun invoke() = statusesRepository.allStatuses
}