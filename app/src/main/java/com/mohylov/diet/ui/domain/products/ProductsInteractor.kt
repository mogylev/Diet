package com.mohylov.diet.ui.domain.products

import com.mohylov.diet.ui.domain.products.entities.ProductItem
import kotlinx.coroutines.flow.Flow

interface ProductsInteractor {

    fun getFoods():Flow<List<ProductItem>>
}