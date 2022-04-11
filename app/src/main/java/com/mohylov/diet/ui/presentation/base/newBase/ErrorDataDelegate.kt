package com.mohylov.diet.ui.presentation.base.newBase

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import com.mohylov.diet.ui.presentation.utils.SingleLiveEvent

interface ErrorActionProvider<T : Any> {
    val errorData: LiveData<T>
}

class ErrorDataDelegate : ErrorActionProvider<Int> {

    private val _errorData = SingleLiveEvent<Int>()

    override val errorData: LiveData<Int>
        get() = _errorData

    fun onError(@StringRes resId: Int) {
        _errorData.setValue(resId)
    }
}