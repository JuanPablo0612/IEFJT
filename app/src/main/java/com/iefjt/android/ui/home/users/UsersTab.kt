package com.iefjt.android.ui.home.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UsersTab(viewModel: UsersViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(uiState.users, key = { it.uid }) { user ->
            UserCard(
                user = user,
                onRoleChange = { viewModel.saveUser(user.uid, user.email, user.role) }
            )
        }
    }
}