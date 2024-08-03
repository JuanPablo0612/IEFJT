package com.iefjt.android.domain.usecase.users

import com.iefjt.android.data.users.UsersRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(uid: String) = usersRepository.getUser(uid)
}