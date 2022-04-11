package com.mohylov.diet.ui.presentation.base.newBase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ViewStateProvider<State : Any> {
    val stateData: LiveData<State>
}

class StateDelegate<State : Any> : ViewStateProvider<State> {

    private val _stateData = MutableLiveData<State>()

    override val stateData: LiveData<State>
        get() = _stateData

    fun updateState(data: State) {
        _stateData.value = data
    }

}