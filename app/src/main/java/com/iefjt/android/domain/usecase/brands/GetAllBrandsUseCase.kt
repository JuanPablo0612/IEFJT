package com.iefjt.android.domain.usecase.brands

import com.iefjt.android.data.brands.BrandsRepository
import javax.inject.Inject

class GetAllBrandsUseCase @Inject constructor(private val brandsRepository: BrandsRepository) {
    operator fun invoke() = brandsRepository.allBrands
}