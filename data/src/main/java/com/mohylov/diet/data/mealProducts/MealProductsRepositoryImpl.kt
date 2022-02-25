package com.mohylov.diet.data.mealProducts

import com.mohylov.diet.domain.mealProducts.entities.MealProductItem
import com.mohylov.diet.domain.toMealProductEntity
import com.mohylov.diet.domain.toMealProductItem
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

    override fun getMealProductsByDate(date: LocalDate): Flow<List<MealProductItem>> {
        return flow {
            emit(
                mealProductDao.getMealProductsByDate(date = date.toString()).map {
                    it.toMealProductItem()
                }
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun insertMealProduct(mealProductItem: MealProductItem) {
        mealProductDao.insertMealProduct(mealProductItem.toMealProductEntity())
    }

    override suspend fun removeMealProduct(productId: Long) {
       withContext(Dispatchers.IO){
           mealProductDao.deleteMealProduct(productId)
       }
    }
}