package com.iefjt.android.domain.usecase.auth

import com.iefjt.android.data.auth.AuthRepository
import com.iefjt.android.domain.usecase.users.SaveUserUseCase
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val saveUserUseCase: SaveUserUseCase
) {
    suspend operator fun invoke(email: String, password: String, name: String): Result<Nothing?> {
       return authRepository.signUp(email, password).fold(
            onSuccess = {
                saveUserUseCase(
                    uid = it,
                    name = name,
                    email = email,
                    canEdit = false,
                    admin = false
                )
            },
            onFailure = {
                Result.failure(it)
            }
        )
    }
}