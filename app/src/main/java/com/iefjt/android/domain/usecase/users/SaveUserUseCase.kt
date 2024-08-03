package com.iefjt.android.domain.usecase.users

import com.iefjt.android.data.users.UsersRepository
import com.iefjt.android.domain.model.User
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(uid: String, name: String, email: String, canEdit: Boolean, admin: Boolean): Result<Nothing?> {
        val user = User(uid = uid, name = name, email = email, canEdit = canEdit, admin = admin)
        return usersRepository.saveUser(user)
    }
}