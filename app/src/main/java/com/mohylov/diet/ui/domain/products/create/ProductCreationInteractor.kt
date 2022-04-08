package com.mohylov.diet.ui.domain.products.create

interface ProductCreationInteractor {

    suspend fun createProduct(
        productName: String,
        caloriesAmount: Int,
        proteinsAmount: Float,
        fatsAmount: Float,
        carbohydratesAmount: Float
    )
}