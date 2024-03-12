package com.iefjt.android.domain.usecase.elements

import com.iefjt.android.data.elements.ElementsRepository
import com.iefjt.android.domain.model.ElementWithAllData
import com.iefjt.android.domain.usecase.brands.GetBrandByIdUseCase
import com.iefjt.android.domain.usecase.headquarters.GetHeadquartersByIdUseCase
import com.iefjt.android.domain.usecase.statuses.GetStatusByIdUseCase
import com.iefjt.android.domain.usecase.types.GetTypeByIdUseCase
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetAllElementsUseCase @Inject constructor(
    private val elementsRepository: ElementsRepository,
    private val getBrandByIdUseCase: GetBrandByIdUseCase,
    private val getHeadquartersByIdUseCase: GetHeadquartersByIdUseCase,
    private val getStatusByIdUseCase: GetStatusByIdUseCase,
    private val getTypeByIdUseCase: GetTypeByIdUseCase
) {
    operator fun invoke() = callbackFlow {
        val elementsWithAllData = mutableListOf<ElementWithAllData>()

        elementsRepository.allElements.collect { elements ->
            elements.forEach { element ->
                combine(
                    getBrandByIdUseCase(element.brandId),
                    getHeadquartersByIdUseCase(element.headquartersId),
                    getStatusByIdUseCase(element.statusId),
                    getTypeByIdUseCase(element.typeId)
                ) { brand, headquarters, status, type ->
                    ElementWithAllData(
                        id = element.id,
                        name = element.name,
                        type = type,
                        brand = brand,
                        serial = element.serial,
                        status = status,
                        headquarters = headquarters,
                        observations = element.observations
                    )
                }.collect { elementWithAllData ->
                    if (elementsWithAllData.any { it.id == elementWithAllData.id }) {
                        val index =
                            elementsWithAllData.indexOfFirst { it.id == elementWithAllData.id }
                        elementsWithAllData[index] = elementWithAllData
                    } else {
                        elementsWithAllData.add(elementWithAllData)
                    }

                    trySend(elementsWithAllData.toList())
                }
            }
        }
    }
}