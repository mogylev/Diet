package com.mohylov.diet.ui.presentation.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.mohylov.diet.ui.presentation.base.NavigationActions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun Fragment.navigate(navigationActions: NavigationActions) {
    findNavController().apply {
        when (navigationActions) {
            is NavigationActions.NavigationAction -> {
                navigate(navigationActions.direction, navigationActions.navOptions)
            }
            is NavigationActions.PopBackStack -> popBackStack()
        }
    }
}

fun TextInputLayout.text(): String {
    return editText?.text.toString()
}

@ExperimentalCoroutinesApi
fun EditText.onTextChaged(): Flow<CharSequence> {
    return callbackFlow {
        val listener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                trySend(s ?: "")
            }

            override fun afterTextChanged(s: Editable?) {}
        }
        addTextChangedListener(listener)
        awaitClose { removeTextChangedListener(listener) }
    }
}