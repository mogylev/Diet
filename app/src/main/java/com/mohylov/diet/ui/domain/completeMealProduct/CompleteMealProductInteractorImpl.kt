package com.mohylov.diet.ui.domain.completeMealProduct

import com.mohylov.diet.ui.data.mealProducts.MealProductsRepository
import com.mohylov.diet.ui.data.products.ProductsRepository
import com.mohylov.diet.ui.domain.completeMealProduct.entities.CompleteMealProductItem
import javax.inject.Inject

class CompleteMealProductInteractorImpl @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val mealProductsRepository: MealProductsRepository
) : CompleteMealProductInteractor {

    override suspend fun getCompleteMealProduct(mealProductId: Long): CompleteMealProductItem {
        val mealProduct = mealProductsRepository.getMealProductById(mealProductId)
        val product = productsRepository.getProductById(mealProduct.productId)
        return CompleteMealProductItem(
            id = mealProduct.id,
            productItem = product,
            name = mealProduct.name,
            amount = mealProduct.amount,
            type = mealProduct.type,
            date = mealProduct.date
        )
    }
}