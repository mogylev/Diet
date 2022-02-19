package com.mohylov.diet.ui.data.mealProducts.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mohylov.diet.ui.domain.mealProducts.entities.MealType
import java.time.LocalDate

@Entity(tableName = "mealProducts")
data class MealProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val protein: Float,
    val fats: Float,
    val carbohydrates: Float,
    val calories: Int,
    val amount: Int,
    val type: MealType,
    val date: LocalDate
)
