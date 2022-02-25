package com.mohylov.diet.data.product

import com.mohylov.diet.domain.products.entities.ProductItem
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    fun getFoods(): Flow<List<ProductItem>>

    fun getFoodsBySearchQuery(searchFilter: String): Flow<List<ProductItem>>
}