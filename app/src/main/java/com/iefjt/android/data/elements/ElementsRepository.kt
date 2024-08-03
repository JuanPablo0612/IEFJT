package com.iefjt.android.data.elements

import com.iefjt.android.data.elements.model.toDomain
import com.iefjt.android.data.elements.remote.ElementsRemoteDataSource
import com.iefjt.android.domain.model.Element
import com.iefjt.android.domain.model.toModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ElementsRepository @Inject constructor(private val remoteDataSource: ElementsRemoteDataSource) {
    fun getAll() = remoteDataSource.getAll().map { elements -> elements.map { it.toDomain() } }

    suspend fun getById(elementId: String) =
        remoteDataSource.getById(elementId).map { it.toDomain() }

    fun getByType(typeId: String) =
        remoteDataSource.getByType(typeId).map { elements -> elements.map { it.toDomain() } }

    suspend fun add(element: Element): Result<Nothing?> {
        val model = element.toModel()
        return remoteDataSource.add(model)
    }

    suspend fun delete(elementId: String): Result<Nothing?> {
        return remoteDataSource.delete(elementId)
    }
}