package com.mohylov.diet.ui.domain.nutrientsCalculator.entities

data class NutrtientResult(
    val totalAmount: Int,
    val totalFats: Float,
    val totalProteins: Float,
    val totalCarbohydrates: Float,
    val totalCalories: Int
) {
    companion object {
        fun empty(): NutrtientResult = NutrtientResult(
            0,
            0f,
            0f,
            0f,
            0
        )
    }
}
