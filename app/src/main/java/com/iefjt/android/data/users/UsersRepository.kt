package com.iefjt.android.data.users

import com.iefjt.android.data.users.model.toDomain
import com.iefjt.android.data.users.remote.UsersRemoteDataSource
import com.iefjt.android.domain.model.User
import com.iefjt.android.domain.model.toModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRepository @Inject constructor(private val remoteDataSource: UsersRemoteDataSource) {
    fun getAll() = remoteDataSource.getUsers().map { users -> users.map { it.toDomain() } }

    suspend fun getUser(uid: String) = remoteDataSource.getUser(uid).map { it.toDomain() }

    suspend fun saveUser(user: User): Result<Nothing?> {
        val userModel = user.toModel()
        return remoteDataSource.saveUser(userModel)
    }
}