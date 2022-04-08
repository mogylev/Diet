package com.mohylov.diet.ui.data.products.entities

import com.google.gson.annotations.SerializedName

/**
 * protein, fats, carbohydrates, calories - values per 100g
 */
data class ProductDto (
    @SerializedName("description")
    val description:String,
    @SerializedName("protein")
    val protein : Float,
    @SerializedName("fats")
    val fats : Float,
    @SerializedName("carbohydrates")
    val carbohydrates : Float,
    @SerializedName("calories")
    val calories : Int,
)
