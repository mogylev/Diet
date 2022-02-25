package com.mohylov.diet.domain.mealProductsCalculator

import com.mohylov.diet.domain.mealProducts.entities.MealProductItem
import com.mohylov.diet.domain.mealProductsCalculator.entities.NutrtientResult

interface MealProductCalculateInteractor {

    suspend fun calculateNutrients(mealProducts: List<MealProductItem>): NutrtientResult
}