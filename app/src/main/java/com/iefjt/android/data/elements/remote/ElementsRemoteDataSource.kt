package com.iefjt.android.data.elements.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import com.iefjt.android.data.elements.model.ElementModel
import com.iefjt.android.data.exceptions.handleExceptions
import com.iefjt.android.data.exceptions.toAppException
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ElementsRemoteDataSource @Inject constructor(firestore: FirebaseFirestore) {
    private val elementsCollection = firestore.collection("iefjt/inventory/elements")

    fun getAll() =
        elementsCollection.dataObjects<ElementModel>()
            .catch { throw (it as Exception).toAppException() }

    suspend fun getById(elementId: String): Result<ElementModel> {
        return handleExceptions {
            val document = elementsCollection.document(elementId).get().await()
            document.toObject<ElementModel>()!!
        }
    }

    fun getByType(typeId: String) =
        elementsCollection.whereEqualTo("typeId", typeId).dataObjects<ElementModel>()
            .catch { throw (it as Exception).toAppException() }

    suspend fun add(elementModel: ElementModel): Result<Nothing?> {
        return handleExceptions {
            val id = elementsCollection.add(elementModel).await().id
            elementsCollection.document(id).update("id", id).await()
            null
        }
    }

    suspend fun delete(elementId: String): Result<Nothing?> {
        return handleExceptions {
            elementsCollection.document(elementId).delete().await()
            null
        }
    }
}