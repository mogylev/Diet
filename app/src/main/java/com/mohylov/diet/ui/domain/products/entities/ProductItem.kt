package com.mohylov.diet.ui.domain.products.entities

data class ProductItem(
    val id:Long,
    val description:String,
    val protein:Float,
    val fats:Float,
    val carbohydrates:Float,
    val calories:Int
)
