package com.mohylov.diet.ui.domain.products.entities

data class ProductItem(
    val id: Long = 0,
    val productName: String,
    val removable: Boolean,
    val protein: Float,
    val fats: Float,
    val carbohydrates: Float,
    val calories: Int
)
