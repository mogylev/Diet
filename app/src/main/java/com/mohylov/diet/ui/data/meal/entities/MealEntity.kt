package com.mohylov.diet.ui.data.meal.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mohylov.diet.ui.data.db.converter.LocalDateConverter
import com.mohylov.diet.ui.domain.meal.entities.MealType
import java.time.LocalDate

@TypeConverters(LocalDateConverter::class)
@Entity
data class MealEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: MealType,
    val date: LocalDate
)