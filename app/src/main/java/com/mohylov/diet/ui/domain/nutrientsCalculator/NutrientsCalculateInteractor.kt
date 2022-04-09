package com.mohylov.diet.ui.domain.nutrientsCalculator

import com.mohylov.diet.ui.domain.mealProducts.entities.MealProductItem
import com.mohylov.diet.ui.domain.nutrientsCalculator.entities.NutrtientResult

interface NutrientsCalculateInteractor {

    suspend fun calculateNutrients(mealProducts: List<MealProductItem>): NutrtientResult
}