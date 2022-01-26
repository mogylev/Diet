package com.mohylov.diet.ui.domain

import com.mohylov.diet.ui.data.product.entities.ProductEntity
import com.mohylov.diet.ui.data.product.entities.Product
import com.mohylov.diet.ui.domain.products.entities.ProductItem

fun ProductEntity.toFoodItem(): ProductItem {
    return ProductItem(
        id = id,
        description = description,
        protein = protein,
        fats = fats,
        carbohydrates = carbohydrates,
        calories = calories
    )
}

fun Product.toFoodEntity(): ProductEntity {
    return ProductEntity(
        description = description,
        protein = protein,
        fats = fats,
        carbohydrates = carbohydrates,
        calories = calories
    )
}