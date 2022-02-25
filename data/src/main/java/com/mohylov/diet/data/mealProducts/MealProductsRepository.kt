package com.mohylov.diet.data.mealProducts

import com.mohylov.diet.domain.mealProducts.entities.MealProductItem
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface MealProductsRepository {

    fun getMealProducts(): Flow<List<MealProductItem>>

    fun getMealProductsByDate(date: LocalDate): Flow<List<MealProductItem>>

    suspend fun insertMealProduct(mealProductItem: MealProductItem)

    suspend fun removeMealProduct(mealProductItem: Long)
}