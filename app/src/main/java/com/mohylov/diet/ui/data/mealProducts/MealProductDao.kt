package com.mohylov.diet.ui.data.mealProducts

import androidx.room.*
import com.mohylov.diet.ui.data.mealProducts.entities.MealProductEntity

@Dao
interface MealProductDao {

    @Query("SELECT * FROM mealProducts WHERE date BETWEEN :startDayMillis AND :endDayMillis ")
    fun getMealProductsByDate(startDayMillis: Long, endDayMillis: Long): List<MealProductEntity>

    @Query("SELECT * FROM mealProducts")
    fun getAllMealProducts(): List<MealProductEntity>

    @Query("SELECT * FROM mealProducts WHERE id = :mealProductId")
    fun getMealProductById(mealProductId: Long): MealProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealProduct(mealProductEntity: MealProductEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMealProduct(mealProductEntity: MealProductEntity): Int

    @Query("DELETE FROM mealProducts WHERE id = :productId")
    suspend fun deleteMealProduct(productId: Long)
}