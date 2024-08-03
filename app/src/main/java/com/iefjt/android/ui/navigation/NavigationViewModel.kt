package com.iefjt.android.ui.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.iefjt.android.domain.usecase.auth.GetIsSignedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(private val getIsSignedInUseCase: GetIsSignedInUseCase) :
    ViewModel() {
    var uiState by mutableStateOf(NavigationUiState())
        private set

    init {
        checkSignedIn()
    }

    private fun checkSignedIn() {
        val isSignedIn = getIsSignedInUseCase()
        val startDestination = if (isSignedIn) Screen.Home.route else Screen.Access.route
        uiState = uiState.copy(startDestination = startDestination)
    }
}

data class NavigationUiState(
    val startDestination: String = ""
)