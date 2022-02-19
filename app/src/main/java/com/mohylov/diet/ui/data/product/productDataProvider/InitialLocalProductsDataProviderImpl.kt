package com.mohylov.diet.ui.data.product.productDataProvider

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mohylov.diet.ui.data.product.entities.ProductDto
import javax.inject.Inject

class InitialLocalProductsDataProviderImpl @Inject constructor(
    private val appContext: Context
) : InitialProductsDataProvider {

    override suspend fun provideFoodData(): List<ProductDto> {
        val foodsGsonString =
            appContext.resources.assets.open("database/food_table.json").bufferedReader().use {
                it.readText()
            }
        val type = object : TypeToken<List<ProductDto>>() {}.type
        return Gson().fromJson(foodsGsonString, type)
    }
}