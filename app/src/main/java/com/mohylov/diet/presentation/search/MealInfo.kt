package com.mohylov.diet.presentation.search

import android.os.Parcelable
import androidx.annotation.StringRes
import com.mohylov.diet.domain.mealProducts.entities.MealType
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealInfo(
    val mealType: MealType,
    val date: String,
    @StringRes val mealNameResId: Int
) : Parcelable
