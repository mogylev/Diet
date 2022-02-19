package com.mohylov.diet.ui.data.product.productDataProvider

import com.mohylov.diet.ui.data.product.entities.ProductDto

interface InitialProductsDataProvider {

    suspend fun provideFoodData():List<ProductDto>
}