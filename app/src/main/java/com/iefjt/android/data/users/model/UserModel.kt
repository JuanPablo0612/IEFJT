package com.iefjt.android.data.users.model

import com.iefjt.android.domain.model.User

data class UserModel(
    val uid: String,
    val name: String,
    val email: String,
    val canEdit: Boolean,
    val admin: Boolean
) {
    constructor() : this(uid = "", name = "", email = "", canEdit = false, admin = false)
}

fun UserModel.toDomain() = User(uid = uid, name = name, email = email, canEdit = canEdit, admin = admin)
