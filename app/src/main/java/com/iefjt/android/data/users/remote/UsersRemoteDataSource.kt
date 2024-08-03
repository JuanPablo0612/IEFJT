package com.iefjt.android.data.users.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import com.iefjt.android.data.exceptions.handleExceptions
import com.iefjt.android.data.exceptions.toAppException
import com.iefjt.android.data.users.model.UserModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersRemoteDataSource @Inject constructor(firestore: FirebaseFirestore) {
    private val usersCollection = firestore.collection("users")

    fun getUsers() = usersCollection.dataObjects<UserModel>().catch { throw (it as Exception).toAppException() }

    suspend fun getUser(uid: String): Result<UserModel> {
        return handleExceptions {
            val user = usersCollection.document(uid).get().await().toObject<UserModel>()!!
            user
        }
    }

    suspend fun saveUser(userModel: UserModel): Result<Nothing?> {
        return handleExceptions {
            usersCollection.document(userModel.uid).set(userModel).await()
            null
        }
    }
}