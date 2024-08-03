package com.iefjt.android.domain.usecase.users

import com.iefjt.android.data.auth.AuthRepository
import com.iefjt.android.data.users.UsersRepository
import com.iefjt.android.domain.model.User
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(): Result<User> {
        val uid = authRepository.getUid()
        return usersRepository.getUser(uid)
    }
}