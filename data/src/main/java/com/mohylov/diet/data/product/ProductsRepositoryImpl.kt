package com.mohylov.diet.data.product

import com.mohylov.diet.domain.products.entities.ProductItem
import com.mohylov.diet.domain.toProductItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val productDao: ProductDao) :
    ProductsRepository {

    override fun getFoods(): Flow<List<ProductItem>> {
        return flow {
            emit(productDao.getAll().map { it.toProductItem() })
        }.flowOn(Dispatchers.IO)
    }

    override fun getFoodsBySearchQuery(searchFilter: String): Flow<List<ProductItem>> {
        return flow {
            emit(productDao.getFilteredProducts(searchFilter).map { it.toProductItem() })
        }.flowOn(Dispatchers.IO)
    }
}