package com.mohylov.diet.ui.domain.completeMealProduct

import com.mohylov.diet.ui.domain.completeMealProduct.entities.CompleteMealProductItem

interface CompleteMealProductInteractor {

    suspend fun getCompleteMealProduct(mealProductId: Long): CompleteMealProductItem
}