package com.mohylov.diet.ui.data.filters

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.time.Instant
import javax.inject.Inject

class FiltersRepositoryImpl @Inject constructor(): FiltersRepository {

    private var selectedDateFlow =
        MutableSharedFlow<Instant>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    init {
        selectedDateFlow.tryEmit(Instant.now())
    }

    override fun getSelectedDate(): Flow<Instant> {
        return selectedDateFlow
    }

    override fun applySelectedDate(newDate: Instant) {
        this.selectedDateFlow.tryEmit(newDate)
    }
}