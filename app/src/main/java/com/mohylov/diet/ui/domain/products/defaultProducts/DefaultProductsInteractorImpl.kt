package com.mohylov.diet.ui.domain.products.defaultProducts

import com.mohylov.diet.ui.data.products.ProductsRepository
import com.mohylov.diet.ui.domain.products.FilterStrategy
import com.mohylov.diet.ui.domain.products.entities.ProductItem
import javax.inject.Inject

class DefaultProductsInteractorImpl @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val filterStrategy: FilterStrategy
) : DefaultProductsInteractor {

    override suspend fun getDefaultProducts(): List<ProductItem> {
        return productsRepository.getProducts().filter { !it.removable }
    }

    override suspend fun searchDefaultProducts(searchFilter: String): List<ProductItem> {
        return filterStrategy.filter(searchFilter, getDefaultProducts())
    }
}