package com.iefjt.android.data.statuses

import com.iefjt.android.data.statuses.model.toDomain
import com.iefjt.android.data.statuses.remote.StatusesRemoteDataSource
import com.iefjt.android.domain.model.Status
import com.iefjt.android.domain.model.toModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StatusesRepository @Inject constructor(private val remoteDataSource: StatusesRemoteDataSource) {
    val allStatuses = remoteDataSource.getAll().map { it.map { model -> model.toDomain() } }

    fun getById(statusId: String) = remoteDataSource.getById(statusId).map { model -> model!!.toDomain() }

    suspend fun add(status: Status) {
        val model = status.toModel()
        remoteDataSource.add(model)
    }

    suspend fun delete(statusId: String) = remoteDataSource.delete(statusId)
}