package com.iefjt.android.data.elements.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import com.iefjt.android.data.elements.model.ElementModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ElementsRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    private val elementsCollection = firestore.collection("iefjt/inventory/elements")

    fun getAll() = elementsCollection.dataObjects<ElementModel>()

    suspend fun getById(elementId: String): ElementModel {
        val document = elementsCollection.document(elementId).get().await()
        return document.toObject<ElementModel>()!!
    }

    suspend fun add(elementModel: ElementModel) {
        val id = elementsCollection.add(elementModel).await().id
        elementsCollection.document(id).update("id", id).await()
    }

    suspend fun delete(elementId: String) {
        elementsCollection.document(elementId).delete().await()
    }
}