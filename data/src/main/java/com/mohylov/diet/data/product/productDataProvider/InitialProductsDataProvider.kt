package com.mohylov.diet.data.product.productDataProvider

import com.mohylov.diet.data.product.entities.ProductDto

interface InitialProductsDataProvider {

    suspend fun provideFoodData():List<ProductDto>
}