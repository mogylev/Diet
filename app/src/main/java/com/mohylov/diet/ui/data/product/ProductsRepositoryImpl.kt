package com.mohylov.diet.ui.data.product

import com.mohylov.diet.ui.data.product.mappers.toProductEntity
import com.mohylov.diet.ui.data.product.mappers.toProductItem
import com.mohylov.diet.ui.domain.products.entities.ProductItem
import com.mohylov.diet.ui.presentation.mappers.toProductViewItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
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

    override suspend fun getProductById(productId: Long): ProductItem {
        return withContext(Dispatchers.IO) {
            productDao.getProductById(productId).toProductItem()
        }
    }

    override suspend fun createProduct(product: ProductItem) {
        return withContext(Dispatchers.IO) {
            productDao.insertProduct(product.toProductEntity())
        }
    }
}