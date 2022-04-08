package com.mohylov.diet.ui.domain.mealProductsCalculator

import com.mohylov.diet.ui.domain.mealProducts.entities.MealProductItem
import com.mohylov.diet.ui.domain.mealProductsCalculator.entities.NutrtientResult

interface MealProductCalculateInteractor {

    suspend fun calculateNutrients(mealProducts: List<MealProductItem>): NutrtientResult
}