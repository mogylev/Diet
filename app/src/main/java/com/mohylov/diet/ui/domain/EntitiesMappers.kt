package com.mohylov.diet.ui.domain

import com.mohylov.diet.ui.data.mealProducts.entities.MealProductEntity
import com.mohylov.diet.ui.data.product.entities.ProductEntity
import com.mohylov.diet.ui.data.product.entities.ProductDto
import com.mohylov.diet.ui.domain.mealProducts.entities.MealProductItem
import com.mohylov.diet.ui.domain.products.entities.ProductItem
import com.mohylov.diet.ui.presentation.main.adapters.ProductViewItem

fun ProductEntity.toProductItem(): ProductItem {
    return ProductItem(
        id = id,
        name = name,
        protein = protein,
        fats = fats,
        carbohydrates = carbohydrates,
        calories = calories
    )
}

fun ProductDto.toProductEntity(): ProductEntity {
    return ProductEntity(
        name = description,
        protein = protein,
        fats = fats,
        carbohydrates = carbohydrates,
        calories = calories
    )
}

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
        date = date
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
        date = date
    )
}


fun List<MealProductItem>.toProductViewItems(): List<ProductViewItem> {
    return this.map { it.toMealProductViewItem() }
}

fun ProductItem.toProductViewItem(): ProductViewItem {
    return ProductViewItem(
        id = id,
        name = name,
        protein = protein,
        fats = fats,
        carbohydrates = carbohydrates,
        calories = calories
    )
}

fun ProductViewItem.toProductItem(): ProductItem {
    return ProductItem(
        id = id,
        name = name,
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