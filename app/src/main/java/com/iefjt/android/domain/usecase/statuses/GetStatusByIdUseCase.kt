package com.iefjt.android.domain.usecase.statuses

import com.iefjt.android.data.statuses.StatusesRepository
import javax.inject.Inject

class GetStatusByIdUseCase @Inject constructor(private val statusesRepository: StatusesRepository) {
    operator fun invoke(statusId: String) = statusesRepository.getById(statusId)
}