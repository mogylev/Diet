package com.mohylov.diet.ui.data.product.mappers

import com.mohylov.diet.ui.data.product.entities.ProductDto
import com.mohylov.diet.ui.data.product.entities.ProductEntity
import com.mohylov.diet.ui.domain.products.entities.ProductItem

fun ProductEntity.toProductItem(): ProductItem {
    return ProductItem(
        id = id,
        productName = name,
        protein = protein,
        fats = fats,
        carbohydrates = carbohydrates,
        calories = calories
    )
}

fun ProductItem.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = productName,
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