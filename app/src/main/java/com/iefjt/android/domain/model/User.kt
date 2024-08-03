package com.iefjt.android.domain.model

import com.iefjt.android.data.users.model.UserModel

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val canEdit: Boolean = false,
    val admin : Boolean = false,
)

fun User.toModel() = UserModel(uid = uid, name = name, email = email, canEdit = canEdit, admin = admin)
