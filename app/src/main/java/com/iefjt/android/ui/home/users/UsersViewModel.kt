package com.iefjt.android.ui.home.users

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iefjt.android.data.exceptions.getMessageId
import com.iefjt.android.domain.model.User
import com.iefjt.android.domain.usecase.users.GetUsersUseCase
import com.iefjt.android.domain.usecase.users.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {
    var uiState by mutableStateOf(UsersUiState())
        private set

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            getUsersUseCase().catch {
                uiState = uiState.copy(errorMessageId = (it as Exception).getMessageId())
            }.collect { users ->
                uiState = uiState.copy(users = users)
            }
        }
    }

    fun saveUser(uid: String, email: String, role: String) {
        viewModelScope.launch {
            try {
                saveUserUseCase(uid, email, role)
            } catch (e: Exception) {
                uiState = uiState.copy(errorMessageId = e.getMessageId())
            }
        }
    }
}

data class UsersUiState(
    val loading: Boolean = true,
    val errorMessageId: Int? = null,
    val users: List<User> = emptyList()
)