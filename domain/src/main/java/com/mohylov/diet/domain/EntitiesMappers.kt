package com.mohylov.diet.domain

import com.mohylov.diet.data.mealProducts.entities.MealProductEntity
import com.mohylov.diet.data.product.entities.ProductEntity
import com.mohylov.diet.data.product.entities.ProductDto
import com.mohylov.diet.domain.mealProducts.entities.MealProductItem
import com.mohylov.diet.domain.products.entities.ProductItem
import com.mohylov.diet.presentation.main.adapters.ProductViewItem

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