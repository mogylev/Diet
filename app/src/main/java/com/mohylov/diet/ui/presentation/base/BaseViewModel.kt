package com.mohylov.diet.ui.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<State, Action> : ViewModel() {

    val stateData = MutableLiveData<State>()
    val actionsData = MutableLiveData<Action>()

}