package com.mohylov.diet.ui.domain.filter

import com.mohylov.diet.ui.data.filters.FiltersRepository
import kotlinx.coroutines.flow.Flow
import java.time.Instant
import javax.inject.Inject

class FiltersInteractorImpl @Inject constructor(private val filtersRepository: FiltersRepository) : FiltersInteractor {

    override fun getSelectedDate(): Flow<Instant> {
        return filtersRepository.getSelectedDate()
    }

    override fun applySelectedDate(newDate: Instant) {
        filtersRepository.applySelectedDate(newDate)
    }
}