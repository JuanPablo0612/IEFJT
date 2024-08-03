package com.iefjt.android.domain.usecase.auth

import com.iefjt.android.data.auth.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) = authRepository.signIn(email, password)
}