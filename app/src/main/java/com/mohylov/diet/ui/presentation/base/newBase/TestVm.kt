package com.mohylov.diet.ui.presentation.base.newBase

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohylov.diet.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class TestVm(
    private val stateDelegate: StateDelegate<TestViewState> = StateDelegate(),
    private val actionDelegate: ActionDelegate<TestViewActions> = ActionDelegate(),
    private val errorDataDelegate: ErrorDataDelegate = ErrorDataDelegate()
) : ViewModel(), ViewStateProvider<TestViewState> by stateDelegate,
    ViewActionProvider<TestViewActions> by actionDelegate,
    ErrorActionProvider<Int> by errorDataDelegate {


    fun test() {
        stateDelegate.updateState(TestViewState(true))

        actionDelegate.updateAction(TestViewActions.Test)
    }

    private fun doSomeSheet() {
        doAsync(
            work = {
                Log.e("tag!!!", "do some sheet: ")
            },
            onWorkError = {
                errorDataDelegate.onError(R.string.error_generic)
            }
        )
    }
}

data class TestViewState(val isLoading: Boolean)

sealed class TestViewActions {
    object Test : TestViewActions()
}

fun ViewModel.doAsync(
    work: suspend CoroutineScope.() -> Unit,
    onWorkError: (Throwable) -> Unit

) {
    viewModelScope.launch {
        try {
            work.invoke(this)
        } catch (exception: Exception) {
            onWorkError(exception)
        }
    }
}
