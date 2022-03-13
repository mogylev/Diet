package com.mohylov.diet.ui.domain.mealProductsManagement

import com.mohylov.diet.ui.data.mealProducts.MealProductsRepository
import com.mohylov.diet.ui.data.product.ProductsRepository
import com.mohylov.diet.ui.domain.mealProducts.entities.MealProductItem
import com.mohylov.diet.ui.domain.mealProducts.entities.MealType
import com.mohylov.diet.ui.domain.products.entities.ProductItem
import java.time.LocalDate
import javax.inject.Inject

class MealProductsManagementImpl @Inject constructor(
    private val mealProductsRepository: MealProductsRepository,
    private val productsRepository: ProductsRepository
) :
    MealProductsManagementInteractor {

    override suspend fun insertMealProduct(
        mealType: MealType,
        productItem: ProductItem,
        date: LocalDate,
        amount: Int
    ) {
        mealProductsRepository.insertMealProduct(
            MealProductItem(
                name = productItem.productName,
                productId = productItem.id,
                protein = (productItem.protein / 100f) * amount,
                fats = (productItem.fats / 100f) * amount,
                carbohydrates = (productItem.carbohydrates / 100f) * amount,
                calories = ((productItem.calories / 100f) * amount).toInt(),
                amount = amount,
                type = mealType,
                date = date
            )
        )
    }

    override suspend fun updateMealProduct(mealProductId: Long, amount: Int) {
        val mealProduct = mealProductsRepository.getMealProductById(mealProductId)
        val product = productsRepository.getProductById(mealProduct.productId)
        mealProductsRepository.updateMealProduct(
            mealProduct.copy(
                protein = (product.protein / 100f) * amount,
                fats = (product.fats / 100f) * amount,
                carbohydrates = (product.carbohydrates / 100f) * amount,
                calories = ((product.calories / 100f) * amount).toInt(),
                amount = amount,
            )
        )
    }

    override suspend fun removeMealProduct(productId: Long) {
        mealProductsRepository.removeMealProduct(productId)
    }
}