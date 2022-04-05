package com.mohylov.diet.ui.domain.products.defaultProducts

import com.mohylov.diet.ui.domain.products.entities.ProductItem

interface DefaultProductsInteractor {

    suspend fun getDefaultProducts(): List<ProductItem>

    suspend fun searchDefaultProducts(searchFilter: String): List<ProductItem>
}