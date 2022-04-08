package com.mohylov.diet.ui.data.mealProducts

import com.mohylov.diet.ui.data.mealProducts.mappers.toMealProductEntity
import com.mohylov.diet.ui.data.mealProducts.mappers.toMealProductItem
import com.mohylov.diet.ui.domain.mealProducts.entities.MealProductItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset.UTC
import javax.inject.Inject

class MealProductsRepositoryImpl @Inject constructor(private val mealProductDao: MealProductDao) :
    MealProductsRepository {

    override fun getAllMealProducts(): Flow<List<MealProductItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMealProductsByDate(date: Instant): List<MealProductItem> {
        return withContext(Dispatchers.IO) {
            val startDay =
                LocalDateTime.ofInstant(date, UTC).toLocalDate().atStartOfDay().toInstant(UTC)
                    .toEpochMilli()
            val endDay = LocalDateTime.ofInstant(date, UTC).toLocalDate().atTime(LocalTime.MAX)
                .toInstant(UTC).toEpochMilli()
            mealProductDao.getMealProductsByDate(
                startDayMillis = startDay,
                endDayMillis = endDay
            ).map {
                it.toMealProductItem()
            }
        }
    }

    override suspend fun getMealProductById(mealProductId: Long): MealProductItem {
        return withContext(Dispatchers.IO) {
            mealProductDao.getMealProductById(mealProductId).toMealProductItem()
        }
    }

    override suspend fun insertMealProduct(mealProductItem: MealProductItem) {
        withContext(Dispatchers.IO) {
            mealProductDao.insertMealProduct(mealProductItem.toMealProductEntity())
        }
    }

    override suspend fun updateMealProduct(mealProductItem: MealProductItem) {
        withContext(Dispatchers.IO) {
            mealProductDao.updateMealProduct(mealProductItem.toMealProductEntity())
        }
    }

    override suspend fun removeMealProduct(productId: Long) {
        withContext(Dispatchers.IO) {
            mealProductDao.deleteMealProduct(productId)
        }
    }
}