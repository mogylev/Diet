package com.mohylov.diet.ui.presentation.search.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AmountInfo(
    val productId: Long,
    val amount: Int
) : Parcelable