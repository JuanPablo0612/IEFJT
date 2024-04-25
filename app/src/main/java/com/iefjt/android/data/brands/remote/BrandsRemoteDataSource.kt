package com.iefjt.android.data.brands.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import com.iefjt.android.data.brands.model.BrandModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BrandsRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    private val brandsCollection = firestore.collection("iefjt/inventory/brands")

    fun getAll() = brandsCollection.dataObjects<BrandModel>()

    suspend fun getById(brandId: String): BrandModel {
        val document = brandsCollection.document(brandId).get().await()
        return document.toObject<BrandModel>()!!
    }

    suspend fun add(brandModel: BrandModel) {
        val id = brandsCollection.add(brandModel).await().id
        brandsCollection.document(id).update("id", id).await()
    }

    suspend fun delete(brandId: String) {
        brandsCollection.document(brandId).delete().await()
    }
}