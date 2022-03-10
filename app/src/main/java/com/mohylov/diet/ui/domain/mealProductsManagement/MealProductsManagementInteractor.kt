package com.mohylov.diet.ui.domain.mealProductsManagement

import com.mohylov.diet.ui.domain.mealProducts.entities.MealType
import com.mohylov.diet.ui.domain.products.entities.ProductItem
import java.time.LocalDate

interface MealProductsManagementInteractor {

    suspend fun insertMealProduct(
        mealType: MealType,
        productItem: ProductItem,
        date: LocalDate,
        amount: Int
    )

    suspend fun updateMealProduct(mealProductId: Long, amount: Int)

    suspend fun removeMealProduct(productId: Long)
}