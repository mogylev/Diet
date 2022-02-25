package com.mohylov.diet.presentation.search

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AmountInfo(
    val productId: Long,
    val amount: Int
) : Parcelable