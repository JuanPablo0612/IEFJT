package com.iefjt.android.data.elements

import com.iefjt.android.data.elements.model.toDomain
import com.iefjt.android.data.elements.remote.ElementsRemoteDataSource
import com.iefjt.android.domain.model.Element
import com.iefjt.android.domain.model.toModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ElementsRepository @Inject constructor(private val remoteDataSource: ElementsRemoteDataSource) {
    val allElements = remoteDataSource.getAll().map { it.map { model -> model.toDomain() } }

    suspend fun getById(elementId: String): Element {
        return remoteDataSource.getById(elementId).toDomain()
    }

    suspend fun add(element: Element) {
        val model = element.toModel()
        remoteDataSource.add(model)
    }

    suspend fun delete(elementId: String) {
        remoteDataSource.delete(elementId)
    }
}