package com.mohylov.diet.ui.presentation.mealEdit.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealProductInfo(
    val id: Long,
    val productName: String,
) : Parcelable
