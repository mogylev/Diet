package com.mohylov.diet.ui.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohylov.diet.ui.data.db.converter.LocalDateConverter
import com.mohylov.diet.ui.data.db.converter.ProductsConverter
import com.mohylov.diet.ui.data.meal.entities.MealEntity
import com.mohylov.diet.ui.data.product.entities.ProductEntity

@TypeConverters(LocalDateConverter::class, ProductsConverter::class)
@Database(entities = [ProductEntity::class, MealEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun foodDao(): ProductDao

    abstract fun mealDao(): MealDao
}