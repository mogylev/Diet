package com.mohylov.diet.ui.presentation.search.entities

import android.os.Parcelable
import androidx.annotation.StringRes
import com.mohylov.diet.ui.domain.mealProducts.entities.MealType
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealInfo(
    val mealType: MealType,
    val date: String,
    @StringRes val mealNameResId: Int
) : Parcelable
