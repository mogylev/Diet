package com.mohylov.diet.ui.presentation.mappers

import com.mohylov.diet.ui.domain.mealProducts.entities.MealProductItem
import com.mohylov.diet.ui.domain.products.entities.ProductItem
import com.mohylov.diet.ui.presentation.main.adapters.ProductViewItem

fun List<MealProductItem>.toProductViewItems(): List<ProductViewItem> {
    return this.map { it.toMealProductViewItem() }
}

fun ProductItem.toProductViewItem(): ProductViewItem {
    return ProductViewItem(
        id = id,
        name = productName,
        protein = protein,
        fats = fats,
        carbohydrates = carbohydrates,
        calories = calories
    )
}

fun ProductViewItem.toProductItem(): ProductItem {
    return ProductItem(
        id = id,
        productName = name,
        protein = protein,
        fats = fats,
        carbohydrates = carbohydrates,
        calories = calories
    )
}

fun MealProductItem.toMealProductViewItem(): ProductViewItem {
    return ProductViewItem(
        id = id,
        name = name,
        protein = protein,
        fats = fats,
        carbohydrates = carbohydrates,
        calories = calories
    )
}