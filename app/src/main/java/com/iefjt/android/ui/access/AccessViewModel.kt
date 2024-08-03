package com.iefjt.android.ui.access

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iefjt.android.data.exceptions.getMessageId
import com.iefjt.android.domain.usecase.auth.SignInUseCase
import com.iefjt.android.domain.usecase.auth.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccessViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    var uiState by mutableStateOf(AccessUiState())
        private set

    fun onEmailChange(text: String) {
        uiState = uiState.copy(email = text)
        validateEmail()
    }

    fun onPasswordChange(text: String) {
        uiState = uiState.copy(password = text)
        validatePassword()
    }

    fun onPasswordVisibilityChange() {
        uiState = uiState.copy(showPassword = !uiState.showPassword)
    }

    private fun validateEmail() {
        val patterns = Patterns.EMAIL_ADDRESS
        val isValidEmail = patterns.matcher(uiState.email).matches()
        uiState = uiState.copy(isValidEmail = isValidEmail)
    }

    private fun validatePassword() {
        val isValidPassword = uiState.password.length in 8..16
        uiState = uiState.copy(isValidPassword = isValidPassword)
    }

    fun onSignIn() {
        viewModelScope.launch {
            uiState = uiState.copy(loading = true)
            try {
                signInUseCase(uiState.email, uiState.password)
                uiState = uiState.copy(success = true, loading = false)
            } catch (e: Exception) {
                uiState = uiState.copy(loading = false, errorMessageId = e.getMessageId())
            }
        }
    }

    fun onSignUp() {
        viewModelScope.launch {
            uiState = uiState.copy(loading = true)
            try {
                signUpUseCase(uiState.email, uiState.password)
                uiState = uiState.copy(success = true, loading = false)
            } catch (e: Exception) {
                uiState = uiState.copy(loading = false, errorMessageId = e.getMessageId())
            }
        }
    }
}

data class AccessUiState(
    val success: Boolean = false,
    val loading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val isValidEmail: Boolean = false,
    val isValidPassword: Boolean = false,
    val showPassword: Boolean = false,
    val errorMessageId: Int? = 0
)