package com.mohylov.diet.ui.data.product.entities

import com.google.gson.annotations.SerializedName

data class Product (
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
