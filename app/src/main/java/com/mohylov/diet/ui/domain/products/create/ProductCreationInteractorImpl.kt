package com.mohylov.diet.ui.domain.products.create

import com.mohylov.diet.ui.data.products.ProductsRepository
import com.mohylov.diet.ui.domain.products.entities.ProductItem
import javax.inject.Inject

class ProductCreationInteractorImpl @Inject constructor(private val productsRepository: ProductsRepository) :
    ProductCreationInteractor {

    override suspend fun createProduct(
        productName: String,
        caloriesAmount: Int,
        proteinsAmount: Float,
        fatsAmount: Float,
        carbohydratesAmount: Float
    ) {
        productsRepository.createProduct(
            ProductItem(
                productName = productName,
                calories = caloriesAmount,
                protein = proteinsAmount,
                fats = fatsAmount,
                carbohydrates = carbohydratesAmount,
                removable = true
            )
        )
    }
}