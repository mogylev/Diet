package com.mohylov.diet.ui.data.mealProducts.mappers

import com.mohylov.diet.ui.data.mealProducts.entities.MealProductEntity
import com.mohylov.diet.ui.domain.mealProducts.entities.MealProductItem
import java.time.Instant

fun MealProductEntity.toMealProductItem(): MealProductItem {
    return MealProductItem(
        id = id,
        productId = productId,
        name = name,
        protein = protein,
        fats = fats,
        carbohydrates = carbohydrates,
        calories = calories,
        amount = amount,
        type = type,
        date = Instant.ofEpochMilli(date)
    )
}

fun MealProductItem.toMealProductEntity(): MealProductEntity {
    return MealProductEntity(
        id = id,
        productId = productId,
        name = name,
        protein = protein,
        fats = fats,
        carbohydrates = carbohydrates,
        calories = calories,
        amount = amount,
        type = type,
        date = date.toEpochMilli()
    )
}