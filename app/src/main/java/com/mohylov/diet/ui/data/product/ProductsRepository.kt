package com.mohylov.diet.ui.data.product

import com.mohylov.diet.ui.domain.products.entities.ProductItem
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    fun getFoods():Flow<List<ProductItem>>
}