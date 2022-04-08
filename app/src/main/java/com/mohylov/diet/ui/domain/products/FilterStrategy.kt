package com.mohylov.diet.ui.domain.products

import com.mohylov.diet.ui.domain.products.entities.ProductItem

interface FilterStrategy {

    suspend fun filter(filterQueary: String, products: List<ProductItem>): List<ProductItem>
}