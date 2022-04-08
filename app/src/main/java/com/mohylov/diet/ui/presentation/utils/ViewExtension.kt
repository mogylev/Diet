package com.mohylov.diet.ui.presentation.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun View.applyInsets(
    left: Boolean = false,
    top: Boolean = false,
    right: Boolean = false,
    bottom: Boolean = false
) {
    setOnApplyWindowInsetsListener(this) { v, insets ->
        val systemInnsetsCompat = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.updatePadding(
            left = if (left) systemInnsetsCompat.left else v.paddingLeft,
            top = if(top) systemInnsetsCompat.top else v.paddingTop,
            right = if(right) systemInnsetsCompat.right else v.paddingRight,
            bottom = if(bottom) systemInnsetsCompat.bottom else v.paddingBottom
        )
        WindowInsetsCompat.CONSUMED
    }

}

fun View.setVisibility(flag: Boolean) {
    visibility = if (flag) View.VISIBLE else View.GONE
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