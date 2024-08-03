package com.iefjt.android.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iefjt.android.domain.model.User
import com.iefjt.android.domain.usecase.users.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getCurrentUserUseCase: GetCurrentUserUseCase) : ViewModel() {
    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            val user = getCurrentUserUseCase()
            uiState = uiState.copy(user = user)
        }
    }
}

data class HomeUiState(
    val user: User = User()
)