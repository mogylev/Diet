package com.mohylov.diet.ui.domain.mealProducts

import com.mohylov.diet.ui.domain.mealProducts.entities.MealProductItem
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface MealProductsInteractor {

    fun getMealProductsByDate(date: LocalDate): Flow<List<MealProductItem>>

    fun getMealProducts(): Flow<List<MealProductItem>>

}