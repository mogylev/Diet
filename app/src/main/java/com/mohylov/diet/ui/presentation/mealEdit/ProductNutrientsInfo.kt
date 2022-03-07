package com.mohylov.diet.ui.presentation.mealEdit

import com.mohylov.diet.R

data class ProductNutrientsInfo(
    val proteins: Float,
    val fats: Float,
    val carbohydrates: Float,
    val calories: Int,
) {
    companion object {
        fun empty(): ProductNutrientsInfo {
            return ProductNutrientsInfo(
                proteins = 0f,
                fats = 0f,
                carbohydrates = 0f,
                calories = 0
            )
        }
    }
}
