package com.iefjt.android.data.auth

import com.iefjt.android.data.auth.remote.AuthRemoteDataSource
import javax.inject.Inject

class AuthRepository @Inject constructor(private val remoteDataSource: AuthRemoteDataSource) {
    suspend fun signIn(email: String, password: String) = remoteDataSource.signIn(email, password)
    suspend fun signUp(email: String, password: String) = remoteDataSource.signUp(email, password)
    fun isSignedIn() = remoteDataSource.isSignedIn()
    fun getUid() = remoteDataSource.getUId()
    fun signOut() = remoteDataSource.signOut()
}