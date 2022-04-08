package com.mohylov.diet.ui.data.mealProducts

import com.mohylov.diet.ui.domain.mealProducts.entities.MealProductItem
import kotlinx.coroutines.flow.Flow
import java.time.Instant

interface MealProductsRepository {

    fun getAllMealProducts(): Flow<List<MealProductItem>>

    suspend fun getMealProductsByDate(date: Instant): List<MealProductItem>

    suspend fun getMealProductById(mealProductId: Long): MealProductItem

    suspend fun insertMealProduct(mealProductItem: MealProductItem)

    suspend fun updateMealProduct(mealProductItem: MealProductItem)

    suspend fun removeMealProduct(productId: Long)
}