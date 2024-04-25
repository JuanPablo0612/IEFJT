package com.iefjt.android.domain.usecase.elements

import com.iefjt.android.data.elements.ElementsRepository
import com.iefjt.android.domain.model.ElementWithAllData
import com.iefjt.android.domain.usecase.brands.GetBrandByIdUseCase
import com.iefjt.android.domain.usecase.headquarters.GetHeadquartersByIdUseCase
import com.iefjt.android.domain.usecase.statuses.GetStatusByIdUseCase
import com.iefjt.android.domain.usecase.types.GetTypeByIdUseCase
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllElementsUseCase @Inject constructor(
    private val elementsRepository: ElementsRepository,
    private val getBrandByIdUseCase: GetBrandByIdUseCase,
    private val getHeadquartersByIdUseCase: GetHeadquartersByIdUseCase,
    private val getStatusByIdUseCase: GetStatusByIdUseCase,
    private val getTypeByIdUseCase: GetTypeByIdUseCase
) {
    operator fun invoke() = elementsRepository.allElements.map { elements ->
        elements.map { element ->
            val brand = getBrandByIdUseCase(element.brandId)
            val headquarters = getHeadquartersByIdUseCase(element.headquartersId)
            val status = getStatusByIdUseCase(element.statusId)
            val type = getTypeByIdUseCase(element.typeId)

            val elementWithAllData = ElementWithAllData(
                id = element.id,
                name = element.name,
                type = type,
                brand = brand,
                serial = element.serial,
                status = status,
                headquarters = headquarters,
                observations = element.observations
            )

            elementWithAllData
        }
    }
}