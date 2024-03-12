package com.iefjt.android.domain.usecase.statuses

import com.iefjt.android.data.statuses.StatusesRepository
import com.iefjt.android.domain.model.Status
import javax.inject.Inject

class AddStatusUseCase @Inject constructor(private val statusesRepository: StatusesRepository) {
    suspend operator fun invoke(name: String, color: String) {
        val status = Status(name = name, color = color)
        statusesRepository.add(status)
    }
}