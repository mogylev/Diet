package com.mohylov.diet.ui.domain.products

import com.mohylov.diet.ui.data.product.ProductsRepository
import com.mohylov.diet.ui.domain.products.entities.ProductItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsInteractorImpl @Inject constructor(private val productsRepository: ProductsRepository) :
    ProductsInteractor {

    override fun getProducts(): Flow<List<ProductItem>> {
        return productsRepository.getFoods()
    }

    override fun getProductsBySearchFilter(searchFilter: String): Flow<List<ProductItem>> {
        return productsRepository.getFoodsBySearchQuery(searchFilter)
    }

    override suspend fun getProductById(productId: Long): ProductItem {
        return productsRepository.getProductById(productId)
    }
}