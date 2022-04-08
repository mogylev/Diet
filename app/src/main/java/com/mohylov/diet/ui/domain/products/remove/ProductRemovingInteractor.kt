package com.mohylov.diet.ui.domain.products.remove

interface ProductRemovingInteractor {

    suspend fun removeProduct(productId: Long)
}