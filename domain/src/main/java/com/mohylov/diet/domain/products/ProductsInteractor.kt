package com.mohylov.diet.domain.products

import com.mohylov.diet.domain.products.entities.ProductItem
import kotlinx.coroutines.flow.Flow

interface ProductsInteractor {

    fun getProducts(): Flow<List<ProductItem>>

    fun getProductsBySearchFilter(searchFilter: String): Flow<List<ProductItem>>
}