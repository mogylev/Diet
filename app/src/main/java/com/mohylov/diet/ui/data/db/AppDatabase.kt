package com.mohylov.diet.ui.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohylov.diet.ui.data.db.converter.ProductsConverter
import com.mohylov.diet.ui.data.mealProducts.MealProductDao
import com.mohylov.diet.ui.data.mealProducts.entities.MealProductEntity
import com.mohylov.diet.ui.data.products.ProductDao
import com.mohylov.diet.ui.data.products.entities.ProductEntity

@TypeConverters(ProductsConverter::class)
@Database(entities = [ProductEntity::class, MealProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productsDao(): ProductDao

    abstract fun mealProductDao(): MealProductDao
}