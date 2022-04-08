package com.mohylov.diet.ui.data.products

import com.mohylov.diet.ui.domain.products.entities.ProductItem
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun getProducts(): List<ProductItem>

    fun searchProducts(searchFilter: String): Flow<List<ProductItem>>

    suspend fun getProductById(productId: Long): ProductItem

    suspend fun createProduct(product: ProductItem)
    
    suspend fun removeProduct(productId: Long)
}