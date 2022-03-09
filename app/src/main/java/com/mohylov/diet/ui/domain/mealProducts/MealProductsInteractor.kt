package com.mohylov.diet.ui.domain.mealProducts

import com.mohylov.diet.ui.domain.mealProducts.entities.MealProductItem
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface MealProductsInteractor {

   suspend fun getMealProductsByDate(date: LocalDate): List<MealProductItem>

    fun getMealProducts(): Flow<List<MealProductItem>>

    suspend fun getMealProductById(mealProductId: Long): MealProductItem

}