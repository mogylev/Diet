package com.mohylov.diet.ui.domain.meal.entities

import com.mohylov.diet.ui.domain.products.entities.ProductItem
import java.time.LocalDate

data class MealItem(
    val id: Int,
    val type: MealType,
    val products: List<ProductItem>,
    val date: LocalDate
)