package com.mohylov.diet.ui.domain.products

import com.mohylov.diet.ui.data.products.ProductsRepository
import com.mohylov.diet.ui.domain.products.entities.ProductItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductsInteractorImpl @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val filterStrategy: FilterStrategy
) : ProductsInteractor {

    override suspend fun getProducts(): List<ProductItem> {
        return productsRepository.getProducts()
    }

    override fun searchProducts(searchFilter: String): Flow<List<ProductItem>> {
        return flow {
            emit(filterStrategy.filter(searchFilter, getProducts()))
        }
    }

    override suspend fun getProductById(productId: Long): ProductItem {
        return productsRepository.getProductById(productId)
    }
}