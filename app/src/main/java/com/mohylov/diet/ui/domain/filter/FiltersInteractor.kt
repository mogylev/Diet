package com.mohylov.diet.ui.domain.filter

import kotlinx.coroutines.flow.Flow
import java.time.Instant

interface FiltersInteractor {

    fun getSelectedDate(): Flow<Instant>

    fun applySelectedDate(newDate: Instant)
}