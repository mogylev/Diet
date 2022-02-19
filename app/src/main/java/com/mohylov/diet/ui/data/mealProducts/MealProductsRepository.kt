package com.mohylov.diet.ui.data.mealProducts

import com.mohylov.diet.ui.domain.mealProducts.entities.MealProductItem
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface MealProductsRepository {

    fun getMealProducts(): Flow<List<MealProductItem>>

    fun getMealProductsByDate(date: LocalDate): Flow<List<MealProductItem>>

    suspend fun insertMealProduct(mealProductItem: MealProductItem)

    suspend fun removeMealProduct(mealProductItem: Long)
}