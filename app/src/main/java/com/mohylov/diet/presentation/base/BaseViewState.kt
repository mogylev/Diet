package com.mohylov.diet.presentation.base

import androidx.lifecycle.LiveData

interface BaseViewState <VS> {

    val viewState: LiveData<VS>

     fun updateState(state:VS)
}