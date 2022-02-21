package com.mohylov.diet.ui.domain.mealProductsCalculator

import com.mohylov.diet.ui.domain.mealProducts.entities.MealProductItem
import com.mohylov.diet.ui.domain.mealProductsCalculator.entities.NutrtientResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MealProductsCalculatorInteractorImpl @Inject constructor() : MealProductCalculateInteractor {

    override suspend fun calculateNutrients(mealProducts: List<MealProductItem>): NutrtientResult {
        return withContext(Dispatchers.Default) {
            var totalAmount = 0
            var totalFats = 0f
            var totalCarbs = 0f
            var totalProteins = 0f
            var totalCalories = 0
            mealProducts.forEach {
                totalAmount += it.amount
                totalFats += it.fats
                totalCarbs += it.carbohydrates
                totalProteins += it.protein
                totalCalories += it.calories
            }
            return@withContext NutrtientResult(
                totalAmount = totalAmount,
                totalFats = totalFats,
                totalCarbohydrates = totalCarbs,
                totalProteins = totalProteins,
                totalCalories = totalCalories
            )
        }
    }
}