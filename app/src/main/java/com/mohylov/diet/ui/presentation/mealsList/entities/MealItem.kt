package com.mohylov.diet.ui.presentation.mealsList.entities

import com.mohylov.diet.R
import com.mohylov.diet.ui.domain.mealProducts.entities.MealType

data class MealItem(
    val mealType: MealType,
    val meanNameResId: Int,
) {

    companion object {
        fun emptyMeals(): List<MealItem> {
            return listOf(
                MealItem(MealType.BREAKFAST, R.string.breakfast),
                MealItem(MealType.BREAKFAST_SNACK, R.string.breakfast_snack),
                MealItem(MealType.LUNCH, R.string.lunch),
                MealItem(MealType.LUNCH_SNACK, R.string.lunch_snack),
                MealItem(MealType.DINNER, R.string.dinner),
            )
        }
    }
}