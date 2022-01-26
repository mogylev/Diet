package com.mohylov.diet.ui.data.db.converter

import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateConverter {

    @TypeConverter
    fun fromLocalDate(date:LocalDate) : String{
        return date.toString()
    }

    @TypeConverter
    fun toLocalDate(dateStr : String) : LocalDate {
        return LocalDate.parse(dateStr)
    }
}