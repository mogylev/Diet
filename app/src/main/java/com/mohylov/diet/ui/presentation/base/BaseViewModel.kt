package com.mohylov.diet.ui.presentation.base

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohylov.diet.ui.presentation.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : Any, Action : Any> : ViewModel() {

    val stateData = MutableLiveData<State>()
    val actionsData = SingleLiveEvent<Action>()
    val navigationData = SingleLiveEvent<NavigationActions>()
    val errorSnackData = SingleLiveEvent<Int?>()
    val infoSnackData = SingleLiveEvent<Int?>()
    val progressBarData = SingleLiveEvent<Boolean>()

    protected fun navigate(navigationAction: NavigationActions) {
        navigationData.setValue(navigationAction)
    }

    protected fun updateState(state: State) {
        stateData.value = state
    }

    protected fun updateAction(action: Action) {
        actionsData.setValue(action)
    }

    protected fun async(
        work: suspend CoroutineScope.() -> Unit,
        onWorkError: (throwable: Throwable) -> Unit = { onError(it) }
    ) {
        viewModelScope.launch {
            try {
                work
            } catch (e: Throwable) {
                onWorkError(e)
            }
        }
    }

    protected  fun showErrorMessage(@StringRes messageId:Int){
        errorSnackData.setValue(messageId)
    }

    protected fun onError(throwable: Throwable) {
        Log.e("BaseViewModel", "${throwable.message}: ")
    }

}