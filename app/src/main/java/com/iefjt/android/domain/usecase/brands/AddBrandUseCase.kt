package com.iefjt.android.domain.usecase.brands

import com.iefjt.android.data.brands.BrandsRepository
import com.iefjt.android.domain.model.Brand
import javax.inject.Inject

class AddBrandUseCase @Inject constructor(private val brandsRepository: BrandsRepository) {
    suspend operator fun invoke(name: String, imageUrl: String) {
        val brand = Brand(name = name, imageUrl = imageUrl)
        brandsRepository.add(brand)
    }
}