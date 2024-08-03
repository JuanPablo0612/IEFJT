package com.iefjt.android.domain.usecase.auth

import com.iefjt.android.data.auth.AuthRepository
import javax.inject.Inject

class GetIsSignedInUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke() = authRepository.isSignedIn()
}