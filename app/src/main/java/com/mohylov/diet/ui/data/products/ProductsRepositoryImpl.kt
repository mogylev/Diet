package com.mohylov.diet.ui.data.products

import com.mohylov.diet.ui.data.products.mappers.toProductEntity
import com.mohylov.diet.ui.data.products.mappers.toProductItem
import com.mohylov.diet.ui.domain.products.entities.ProductItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val productDao: ProductDao) :
    ProductsRepository {

    override suspend fun getProducts(): List<ProductItem> {
        return withContext(Dispatchers.IO) {
            productDao.getAll().map { it.toProductItem() }
        }
    }

    override fun searchProducts(searchFilter: String): Flow<List<ProductItem>> {
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
        withContext(Dispatchers.IO) {
            productDao.insertProduct(product.toProductEntity())
        }
    }

    override suspend fun removeProduct(productId: Long) {
        withContext(Dispatchers.IO) {
            productDao.removeProduct(productId)
        }
    }
}