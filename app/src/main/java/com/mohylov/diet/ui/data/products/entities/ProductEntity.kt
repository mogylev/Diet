package com.mohylov.diet.ui.data.products.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val protein: Float,
    val removable: Boolean,
    val fats: Float,
    val carbohydrates: Float,
    val calories: Int
)