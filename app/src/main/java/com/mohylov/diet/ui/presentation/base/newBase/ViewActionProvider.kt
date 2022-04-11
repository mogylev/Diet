package com.mohylov.diet.ui.presentation.base.newBase

import androidx.lifecycle.LiveData
import com.mohylov.diet.ui.presentation.utils.SingleLiveEvent

interface ViewActionProvider<Action : Any> {
    val actionData: LiveData<Action>
}

class ActionDelegate<Action : Any> : ViewActionProvider<Action> {

    private val _actionData = SingleLiveEvent<Action>()

    override val actionData: LiveData<Action>
        get() = _actionData

    fun updateAction(actionData: Action) {
        _actionData.setValue(actionData)
    }

}