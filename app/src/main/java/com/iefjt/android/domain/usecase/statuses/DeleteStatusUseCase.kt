package com.iefjt.android.domain.usecase.statuses

import com.iefjt.android.data.statuses.StatusesRepository
import javax.inject.Inject

class DeleteStatusUseCase @Inject constructor(private val statusesRepository: StatusesRepository) {
    suspend operator fun invoke(statusId: String) = statusesRepository.delete(statusId)
}