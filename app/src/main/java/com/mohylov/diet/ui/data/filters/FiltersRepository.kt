package com.mohylov.diet.ui.data.filters

import kotlinx.coroutines.flow.Flow
import java.time.Instant

interface FiltersRepository {

    fun getSelectedDate(): Flow<Instant>

    fun applySelectedDate(newDate: Instant)
}