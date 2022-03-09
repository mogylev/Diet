package com.mohylov.diet.ui.data.mealProducts

import com.mohylov.diet.ui.domain.mealProducts.entities.MealProductItem
import com.mohylov.diet.ui.domain.toMealProductEntity
import com.mohylov.diet.ui.domain.toMealProductItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

class MealProductsRepositoryImpl @Inject constructor(private val mealProductDao: MealProductDao) :
    MealProductsRepository {

    override fun getMealProducts(): Flow<List<MealProductItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMealProductsByDate(date: LocalDate): List<MealProductItem> {
        return withContext(Dispatchers.IO) {
            mealProductDao.getMealProductsByDate(date = date.toString()).map {
                it.toMealProductItem()
            }
        }
    }

    override suspend fun getMealProductById(mealProductId: Long): MealProductItem {
       return withContext(Dispatchers.IO){
            mealProductDao.getMealProductById(mealProductId).toMealProductItem()
        }
    }

    override suspend fun insertMealProduct(mealProductItem: MealProductItem) {
        withContext(Dispatchers.IO){
            mealProductDao.insertMealProduct(mealProductItem.toMealProductEntity())
        }
    }

    override suspend fun updateMealProduct(mealProductItem: MealProductItem) {
        withContext(Dispatchers.IO) {
            mealProductDao.updateMealProduct(mealProductItem.toMealProductEntity())
        }
    }

    override suspend fun removeMealProduct(productId: Long) {
       withContext(Dispatchers.IO){
           mealProductDao.deleteMealProduct(productId)
       }
    }
}