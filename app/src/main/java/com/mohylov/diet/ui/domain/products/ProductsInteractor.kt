package com.mohylov.diet.ui.domain.products

import com.mohylov.diet.ui.domain.products.entities.ProductItem
import kotlinx.coroutines.flow.Flow

interface ProductsInteractor {

    fun getProductsBySearchFilter(searchFilter: String): Flow<List<ProductItem>>

    suspend fun getProductById(productId: Long): ProductItem

    suspend fun createProduct(
        productName: String,
        caloriesAmount: Int,
        proteinsAmount: Float,
        fatsAmount: Float,
        carbohydratesAmount: Float
    )
}