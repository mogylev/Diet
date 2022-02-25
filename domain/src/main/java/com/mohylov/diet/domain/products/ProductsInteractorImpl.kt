package com.mohylov.diet.domain.products

import com.mohylov.diet.domain.products.ProductsInteractor
import com.mohylov.diet.data.product.ProductsRepository
import com.mohylov.diet.domain.products.entities.ProductItem
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
}