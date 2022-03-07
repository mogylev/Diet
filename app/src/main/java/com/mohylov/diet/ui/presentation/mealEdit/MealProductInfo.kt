package com.mohylov.diet.ui.presentation.mealEdit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealProductInfo(
    val id: Long,
    val productName: String
) : Parcelable
