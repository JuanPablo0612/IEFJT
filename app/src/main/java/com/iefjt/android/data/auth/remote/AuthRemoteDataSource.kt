package com.iefjt.android.data.auth.remote

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.iefjt.android.data.exceptions.handleExceptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val auth: FirebaseAuth) {
    private val currentUser: FirebaseUser?
        get() = auth.currentUser

    suspend fun signIn(email: String, password: String): Result<Nothing?> {
        return handleExceptions {
            auth.signInWithEmailAndPassword(email, password).await()
            null
        }
    }

    suspend fun signUp(email: String, password: String): Result<String> {
        return handleExceptions {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            result.user!!.uid
        }
    }

    fun isSignedIn() = currentUser != null

    fun getUId() = currentUser?.uid ?: ""

    fun signOut() = auth.signOut()
}