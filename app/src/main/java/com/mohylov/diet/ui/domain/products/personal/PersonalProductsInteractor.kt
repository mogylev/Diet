package com.mohylov.diet.ui.domain.products.personal

import com.mohylov.diet.ui.domain.products.entities.ProductItem

interface PersonalProductsInteractor {

    suspend fun getPersonalProducts(): List<ProductItem>

    suspend fun searchPersonalProducts(searchFilter: String): List<ProductItem>
}