package com.iefjt.android.domain.usecase.brands

import com.iefjt.android.data.brands.BrandsRepository
import javax.inject.Inject

class DeleteBrandUseCase @Inject constructor(private val brandsRepository: BrandsRepository) {
    suspend operator fun invoke(brandId: String) = brandsRepository.delete(brandId)
}