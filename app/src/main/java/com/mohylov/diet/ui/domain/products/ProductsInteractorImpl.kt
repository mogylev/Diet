package com.mohylov.diet.ui.domain.products

import com.mohylov.diet.ui.data.product.ProductsRepository
import com.mohylov.diet.ui.domain.products.entities.ProductItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsInteractorImpl @Inject constructor(private val productsRepository: ProductsRepository) : ProductsInteractor {

    override fun getFoods(): Flow<List<ProductItem>> {
        return productsRepository.getFoods()
    }
}