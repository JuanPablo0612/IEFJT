package com.iefjt.android.data.brands.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.iefjt.android.data.brands.model.BrandModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BrandsRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    private val brandsCollection = firestore.collection("iefjt/inventory/brands")

    fun getAll() = brandsCollection.dataObjects<BrandModel>()

    fun getById(brandId: String) = brandsCollection.document(brandId).dataObjects<BrandModel>()

    suspend fun add(brandModel: BrandModel) {
        val id = brandsCollection.add(brandModel).await().id
        brandsCollection.document(id).update("id", id).await()
    }

    suspend fun delete(brandId: String) = brandsCollection.document(brandId).delete().await()
}