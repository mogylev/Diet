package com.mohylov.diet.ui.domain.mealProducts

import com.mohylov.diet.ui.data.mealProducts.MealProductsRepository
import com.mohylov.diet.ui.domain.mealProducts.entities.MealProductItem
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class MealProductsInteractorImpl @Inject constructor(private val mealProductsRepository: MealProductsRepository) :
    MealProductsInteractor {

    override fun getMealProductsByDate(date: LocalDate): Flow<List<MealProductItem>> {
        return mealProductsRepository.getMealProductsByDate(date = date)
    }

    override fun getMealProducts(): Flow<List<MealProductItem>> {
        return mealProductsRepository.getMealProducts()
    }

    override suspend fun getMealProductById(mealProductId: Long): MealProductItem {
        return mealProductsRepository.getMealProductById(mealProductId)
    }
}