package com.mohylov.diet.ui.domain.mealProducts.entities

import java.time.LocalDate

data class MealProductItem(
    val id: Long = 0,
    val productId: Long,
    val name: String,
    val protein: Float,
    val fats: Float,
    val carbohydrates: Float,
    val calories: Int,
    val amount: Int,
    val type: MealType,
    val date: LocalDate
)
