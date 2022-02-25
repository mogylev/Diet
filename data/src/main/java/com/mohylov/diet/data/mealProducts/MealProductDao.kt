package com.mohylov.diet.data.mealProducts

import androidx.room.*
import com.mohylov.diet.data.mealProducts.entities.MealProductEntity

@Dao
interface MealProductDao {

    @Query("SELECT * FROM mealProducts WHERE date = :date ")
    fun getMealProductsByDate(date: String): List<MealProductEntity>

    @Query("SELECT * FROM mealProducts")
    fun getAllMealProducts(): List<MealProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealProduct(mealProductEntity: MealProductEntity)

    @Query("DELETE FROM mealProducts WHERE id = :productId")
    suspend fun deleteMealProduct(productId: Long)
}