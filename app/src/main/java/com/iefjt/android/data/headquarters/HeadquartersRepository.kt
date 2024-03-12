package com.iefjt.android.data.headquarters

import com.iefjt.android.data.headquarters.model.toDomain
import com.iefjt.android.data.headquarters.remote.HeadquartersRemoteDataSource
import com.iefjt.android.domain.model.Headquarters
import com.iefjt.android.domain.model.toModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HeadquartersRepository @Inject constructor(private val remoteDataSource: HeadquartersRemoteDataSource) {
    val allHeadquarters = remoteDataSource.getAll().map { it.map { model -> model.toDomain() } }

    fun getById(headquartersId: String) = remoteDataSource.getById(headquartersId).map { model -> model!!.toDomain() }

    suspend fun add(headquarters: Headquarters) {
        val model = headquarters.toModel()
        remoteDataSource.add(model)
    }

    suspend fun delete(headquartersId: String) = remoteDataSource.delete(headquartersId)
}