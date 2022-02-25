package com.mohylov.diet.domain.mealProductsManagement

import com.mohylov.diet.domain.mealProducts.entities.MealType
import com.mohylov.diet.domain.products.entities.ProductItem
import java.time.LocalDate

interface MealProductsManagementInteractor {

    suspend fun insertMealProduct(
        mealType: MealType,
        productItem: ProductItem,
        date: LocalDate,
        amount: Int
    )

    suspend fun removeMealProduct(productId: Long)
}