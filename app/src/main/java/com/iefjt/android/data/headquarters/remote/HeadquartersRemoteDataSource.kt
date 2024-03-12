package com.iefjt.android.data.headquarters.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.iefjt.android.data.headquarters.model.HeadquartersModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HeadquartersRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    private val headquartersCollection = firestore.collection("iefjt/inventory/headquarters")

    fun getAll() = headquartersCollection.dataObjects<HeadquartersModel>()

    fun getById(headquartersId: String) =
        headquartersCollection.document(headquartersId).dataObjects<HeadquartersModel>()

    suspend fun add(headquartersModel: HeadquartersModel) {
        val id = headquartersCollection.add(headquartersModel).await().id
        headquartersCollection.document(id).update("id", id).await()
    }

    suspend fun delete(headquartersId: String) =
        headquartersCollection.document(headquartersId).delete().await()
}