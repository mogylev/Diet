package com.mohylov.diet.ui.data.meal.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mohylov.diet.ui.data.db.converter.LocalDateConverter
import com.mohylov.diet.ui.data.db.converter.ProductsConverter
import com.mohylov.diet.ui.data.product.entities.ProductEntity
import com.mohylov.diet.ui.domain.meal.entities.MealType
import java.time.LocalDate

@Entity
data class MealEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: MealType,
    val products: List<ProductEntity> = emptyList(),
    val date: LocalDate
)