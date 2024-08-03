package com.iefjt.android.domain.usecase.users

import com.iefjt.android.data.users.UsersRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: UsersRepository) {
    operator fun invoke() = repository.getAll()
}