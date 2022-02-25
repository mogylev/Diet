package com.mohylov.diet.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohylov.diet.data.db.converter.LocalDateConverter
import com.mohylov.diet.data.db.converter.ProductsConverter
import com.mohylov.diet.data.mealProducts.MealProductDao
import com.mohylov.diet.data.mealProducts.entities.MealProductEntity
import com.mohylov.diet.data.product.ProductDao
import com.mohylov.diet.data.product.entities.ProductEntity

@TypeConverters(LocalDateConverter::class, ProductsConverter::class)
@Database(entities = [ProductEntity::class, MealProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productsDao(): ProductDao

    abstract fun mealProductDao(): MealProductDao
}