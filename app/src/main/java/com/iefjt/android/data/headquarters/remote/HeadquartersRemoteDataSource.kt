package com.iefjt.android.data.headquarters.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import com.iefjt.android.data.headquarters.model.HeadquartersModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HeadquartersRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    private val headquartersCollection = firestore.collection("iefjt/inventory/headquarters")

    fun getAll() = headquartersCollection.dataObjects<HeadquartersModel>()

    suspend fun getById(headquartersId: String): HeadquartersModel {
        val document = headquartersCollection.document(headquartersId).get().await()
        return document.toObject<HeadquartersModel>()!!
    }

    suspend fun add(headquartersModel: HeadquartersModel) {
        val id = headquartersCollection.add(headquartersModel).await().id
        headquartersCollection.document(id).update("id", id).await()
    }

    suspend fun delete(headquartersId: String) {
        headquartersCollection.document(headquartersId).delete().await()
    }
}