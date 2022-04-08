package com.mohylov.diet.ui.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mohylov.diet.ui.data.products.entities.ProductEntity

class ProductsConverter {

    @TypeConverter
    fun fromProducts(productList: List<ProductEntity>): String {
        return Gson().toJson(productList)
    }

    @TypeConverter
    fun toProducts(productsJson: String): List<ProductEntity> {
        val productType = object : TypeToken<List<ProductEntity>>() {}.type
        return Gson().fromJson(productsJson, productType)
    }
}