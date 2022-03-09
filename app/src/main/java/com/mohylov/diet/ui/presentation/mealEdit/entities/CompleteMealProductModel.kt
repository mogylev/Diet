package com.mohylov.diet.ui.presentation.mealEdit.entities

import com.mohylov.diet.ui.domain.mealProducts.entities.MealType
import com.mohylov.diet.ui.domain.products.entities.ProductItem
import java.time.LocalDate

data class CompleteMealProductModel(
    val amount:Int,
    val mealType:MealType,
    val productItem: ProductItem,
    val date:LocalDate
)
