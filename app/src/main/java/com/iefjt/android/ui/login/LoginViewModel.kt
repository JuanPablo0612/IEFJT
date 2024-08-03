package com.iefjt.android.ui.login

import androidx.lifecycle.ViewModel
import com.iefjt.android.domain.usecase.auth.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val signInUseCase: SignInUseCase) : ViewModel() {
}