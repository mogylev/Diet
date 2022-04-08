package com.mohylov.diet.ui.domain.mealProducts

import com.mohylov.diet.ui.data.mealProducts.MealProductsRepository
import com.mohylov.diet.ui.domain.mealProducts.entities.MealProductItem
import kotlinx.coroutines.flow.Flow
import java.time.Instant
import javax.inject.Inject

class MealProductsInteractorImpl @Inject constructor(private val mealProductsRepository: MealProductsRepository) :
    MealProductsInteractor {

    override suspend fun getMealProductsByDate(date: Instant): List<MealProductItem> {
        return mealProductsRepository.getMealProductsByDate(date = date)
    }

    override fun getMealProducts(): Flow<List<MealProductItem>> {
        return mealProductsRepository.getAllMealProducts()
    }

    override suspend fun getMealProductById(mealProductId: Long): MealProductItem {
        return mealProductsRepository.getMealProductById(mealProductId)
    }
}