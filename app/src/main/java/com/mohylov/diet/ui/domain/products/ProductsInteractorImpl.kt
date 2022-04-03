package com.mohylov.diet.ui.domain.products

import com.mohylov.diet.ui.data.products.ProductsRepository
import com.mohylov.diet.ui.domain.products.entities.ProductItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsInteractorImpl @Inject constructor(private val productsRepository: ProductsRepository) :
    ProductsInteractor {

    override fun searchProducts(searchFilter: String): Flow<List<ProductItem>> {
        return productsRepository.searchProducts(searchFilter)
    }

    override suspend fun getProductById(productId: Long): ProductItem {
        return productsRepository.getProductById(productId)
    }

    override suspend fun createProduct(
        productName: String,
        caloriesAmount: Int,
        proteinsAmount: Float,
        fatsAmount: Float,
        carbohydratesAmount: Float
    ) {
        productsRepository.createProduct(
            ProductItem(
                productName = productName,
                removable = true,
                calories = caloriesAmount,
                protein = proteinsAmount,
                fats = fatsAmount,
                carbohydrates = carbohydratesAmount
            )
        )
    }
}