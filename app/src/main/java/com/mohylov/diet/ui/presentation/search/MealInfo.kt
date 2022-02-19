package com.mohylov.diet.ui.presentation.search

import android.os.Parcelable
import com.mohylov.diet.ui.domain.mealProducts.entities.MealType
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealInfo(
    val mealType: MealType,
    val date: String,
) : Parcelable
