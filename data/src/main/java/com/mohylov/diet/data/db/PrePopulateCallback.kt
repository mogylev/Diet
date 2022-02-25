package com.mohylov.diet.data.db

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mohylov.diet.data.product.ProductDao
import com.mohylov.diet.data.product.entities.ProductEntity
import com.mohylov.diet.data.product.productDataProvider.InitialProductsDataProvider
import com.mohylov.diet.domain.toProductEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider


class PrePopulateCallback @Inject constructor(
    private val productDaoProvider: Provider<ProductDao>,
    private val productsDataProvider: InitialProductsDataProvider,
    private val coroutineScope: CoroutineScope
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        coroutineScope.launch {
            val initialFoodList = productsDataProvider.provideFoodData().map { it.toProductEntity() }
            populateFoodDao(initialFoodList)
        }
    }

    private fun populateFoodDao(initialProductList: List<ProductEntity>) {
        val foodDao = productDaoProvider.get()
        foodDao.insertAllFoods(initialProductList)
    }

}