package com.mohylov.diet.ui.data.products.productDataProvider

import com.mohylov.diet.ui.data.products.entities.ProductDto

interface DefaultProductsDataProvider {

    suspend fun getDefaultProducts():List<ProductDto>
}