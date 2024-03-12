package com.iefjt.android.data.types

import com.iefjt.android.data.types.model.toDomain
import com.iefjt.android.data.types.remote.TypesRemoteDataSource
import com.iefjt.android.domain.model.Type
import com.iefjt.android.domain.model.toModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TypesRepository @Inject constructor(private val remoteDataSource: TypesRemoteDataSource) {
    val allTypes = remoteDataSource.getAll().map { it.map { model -> model.toDomain() } }

    fun getById(typeId: String) = remoteDataSource.getById(typeId).map { model -> model!!.toDomain() }

    suspend fun add(type: Type) {
        val model = type.toModel()
        remoteDataSource.add(model)
    }

    suspend fun delete(typeId: String) = remoteDataSource.delete(typeId)
}