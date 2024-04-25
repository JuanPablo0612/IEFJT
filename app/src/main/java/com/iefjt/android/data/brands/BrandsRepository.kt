package com.iefjt.android.data.brands

import com.iefjt.android.data.brands.model.toDomain
import com.iefjt.android.data.brands.remote.BrandsRemoteDataSource
import com.iefjt.android.domain.model.Brand
import com.iefjt.android.domain.model.toModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BrandsRepository @Inject constructor(private val remoteDataSource: BrandsRemoteDataSource) {
    val allBrands = remoteDataSource.getAll().map { it.map { model -> model.toDomain() } }

    suspend fun getById(brandId: String): Brand {
        return remoteDataSource.getById(brandId).toDomain()
    }

    suspend fun add(brand: Brand) {
        val model = brand.toModel()
        remoteDataSource.add(model)
    }

    suspend fun delete(brandId: String) {
        remoteDataSource.delete(brandId)
    }
}