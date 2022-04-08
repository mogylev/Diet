package com.mohylov.diet.ui.domain.mealProductsManagement

import com.mohylov.diet.ui.domain.mealProducts.entities.MealType
import java.time.Instant

interface MealProductsManagementInteractor {

    suspend fun insertMealProduct(
        mealType: MealType,
        productId: Long,
        date: Instant,
        amount: Int
    )

    suspend fun updateMealProduct(mealProductId: Long, amount: Int)

    suspend fun removeMealProduct(productId: Long)
}