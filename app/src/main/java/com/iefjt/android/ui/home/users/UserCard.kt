package com.iefjt.android.ui.home.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iefjt.android.R
import com.iefjt.android.domain.model.User

@Composable
fun UserCard(user: User, onRoleChange: (role: String) -> Unit) {
    OutlinedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = user.email, style = MaterialTheme.typography.titleSmall)

            RoleSelector(user.role, onRoleChange)
        }
    }
}

@Composable
fun RoleSelector(selectedRole: String, onRoleChange: (role: String) -> Unit) {
    val roles = listOf(
        stringResource(id = R.string.role_admin),
        stringResource(id = R.string.role_editor),
        stringResource(id = R.string.role_user)
    )

    Column(verticalArrangement = Arrangement.spacedBy(5.dp), modifier = Modifier.fillMaxWidth()) {
        roles.forEach { role ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = role,
                    modifier = Modifier
                        .clickable(enabled = role != selectedRole) {
                            onRoleChange(role)
                        }
                        .weight(1f)
                )

                if (role == selectedRole) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}