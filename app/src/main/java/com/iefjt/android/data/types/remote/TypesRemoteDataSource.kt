package com.iefjt.android.data.types.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import com.iefjt.android.data.types.model.TypeModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TypesRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    private val typesCollection = firestore.collection("iefjt/inventory/types")

    fun getAll() = typesCollection.dataObjects<TypeModel>()

    suspend fun getById(typeId: String): TypeModel {
        val document = typesCollection.document(typeId).get().await()
        return document.toObject<TypeModel>()!!
    }

    suspend fun add(typeModel: TypeModel) {
        val id = typesCollection.add(typeModel).await().id
        typesCollection.document(id).update("id", id).await()
    }

    suspend fun delete(typeId: String) {
        typesCollection.document(typeId).delete().await()
    }
}