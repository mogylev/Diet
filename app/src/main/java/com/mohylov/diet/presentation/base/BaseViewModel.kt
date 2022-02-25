package com.mohylov.diet.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohylov.diet.presentation.utils.SingleLiveEvent

abstract class BaseViewModel<State : Any, Action : Any> : ViewModel() {

    val stateData = MutableLiveData<State>()
    val actionsData = SingleLiveEvent<Action>()
    val navigationData = SingleLiveEvent<NavigationActions>()

    protected fun navigate(navigationAction: NavigationActions) {
        navigationData.setValue(navigationAction)
    }

    protected fun updateState(state: State) {
        stateData.value = state
    }

    protected fun updateAction(action: Action) {
        actionsData.setValue(action)
    }

}