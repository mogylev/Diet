package com.mohylov.diet.ui.domain.products.remove

import com.mohylov.diet.ui.data.products.ProductsRepository
import javax.inject.Inject

class ProductRemovingInteractorImpl @Inject constructor(private val productsRepository: ProductsRepository) :
    ProductRemovingInteractor {

    override suspend fun removeProduct(productId: Long) {
        productsRepository.removeProduct(productId)
    }

}