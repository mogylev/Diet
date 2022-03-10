package com.mohylov.diet.ui.domain.completeMealProduct.entities

import com.mohylov.diet.ui.domain.mealProducts.entities.MealType
import com.mohylov.diet.ui.domain.products.entities.ProductItem
import java.time.LocalDate

data class CompleteMealProductItem(
    val id: Long = 0,
    val productItem: ProductItem,
    val name: String,
    val amount: Int,
    val type: MealType,
    val date: LocalDate
)
