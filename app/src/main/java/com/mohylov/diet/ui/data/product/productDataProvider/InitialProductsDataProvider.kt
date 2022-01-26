package com.mohylov.diet.ui.data.product.productDataProvider

import com.mohylov.diet.ui.data.product.entities.Product

interface InitialProductsDataProvider {

    suspend fun provideFoodData():List<Product>
}