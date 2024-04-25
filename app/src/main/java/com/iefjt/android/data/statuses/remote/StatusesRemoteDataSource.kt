package com.iefjt.android.data.statuses.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import com.iefjt.android.data.statuses.model.StatusModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StatusesRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    private val statusesCollection = firestore.collection("iefjt/inventory/statuses")

    fun getAll() = statusesCollection.dataObjects<StatusModel>()

    suspend fun getById(statusId: String): StatusModel {
        val document = statusesCollection.document(statusId).get().await()
        return document.toObject<StatusModel>()!!
    }

    suspend fun add(statusModel: StatusModel) {
        val id = statusesCollection.add(statusModel).await().id
        statusesCollection.document(id).update("id", id).await()
    }

    suspend fun delete(statusId: String) {
        statusesCollection.document(statusId).delete().await()
    }
}