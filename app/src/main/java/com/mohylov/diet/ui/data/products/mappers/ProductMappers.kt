package com.mohylov.diet.ui.data.products.mappers

import com.mohylov.diet.ui.data.products.entities.ProductDto
import com.mohylov.diet.ui.data.products.entities.ProductEntity
import com.mohylov.diet.ui.domain.products.entities.ProductItem

fun ProductEntity.toProductItem(): ProductItem {
    return ProductItem(
        id = id,
        productName = name,
        protein = protein,
        removable = removable,
        fats = fats,
        carbohydrates = carbohydrates,
        calories = calories
    )
}

fun ProductItem.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = productName,
        removable = removable,
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
        removable = false,
        fats = fats,
        carbohydrates = carbohydrates,
        calories = calories
    )
}