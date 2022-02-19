package com.mohylov.diet.ui.presentation.utils

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.showDialog(
    titleResId: Int? = null,
    messageResId: Int? = null,
    positiveBtnResId: Int? = null,
    negativeBtnResId: Int? = null,
    positiveListener: () -> Unit = {},
    negativeListener: () -> Unit = {}
) {
    val builder = MaterialAlertDialogBuilder(this)
    titleResId?.let { builder.setTitle(it) }
    messageResId?.let { builder.setMessage(messageResId) }
    positiveBtnResId?.let {
        builder.setPositiveButton(it) { d, w -> positiveListener.invoke() }
    }
    negativeBtnResId?.let {
        builder.setNegativeButton(it) { d, w -> negativeListener.invoke() }
    }
    builder.create().show()
}