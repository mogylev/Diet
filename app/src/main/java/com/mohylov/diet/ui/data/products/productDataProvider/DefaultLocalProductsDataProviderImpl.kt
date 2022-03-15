package com.mohylov.diet.ui.data.products.productDataProvider

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mohylov.diet.ui.data.products.entities.ProductDto
import javax.inject.Inject

class DefaultLocalProductsDataProviderImpl @Inject constructor(
    private val appContext: Context
) : DefaultProductsDataProvider {

    override suspend fun getDefaultProducts(): List<ProductDto> {
        val foodsGsonString =
            appContext.resources.assets.open("database/food_table.json").bufferedReader().use {
                it.readText()
            }
        val type = object : TypeToken<List<ProductDto>>() {}.type
        return Gson().fromJson(foodsGsonString, type)
    }
}