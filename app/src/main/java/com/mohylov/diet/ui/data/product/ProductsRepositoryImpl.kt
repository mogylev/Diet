package com.mohylov.diet.ui.data.product

import com.mohylov.diet.ui.data.db.ProductDao
import com.mohylov.diet.ui.domain.products.entities.ProductItem
import com.mohylov.diet.ui.domain.toFoodItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val productDao: ProductDao) : ProductsRepository {


    override fun getFoods(): Flow<List<ProductItem>> {
        return productDao.getAll().map { foodEntities ->
            foodEntities.map { it.toFoodItem() }
        }
    }
}