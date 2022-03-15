package com.mohylov.diet.ui.data.db

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mohylov.diet.ui.data.products.ProductDao
import com.mohylov.diet.ui.data.products.entities.ProductEntity
import com.mohylov.diet.ui.data.products.mappers.toProductEntity
import com.mohylov.diet.ui.data.products.productDataProvider.DefaultProductsDataProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider


class PrePopulateCallback @Inject constructor(
    private val productDaoProvider: Provider<ProductDao>,
    private val productsDataProvider: DefaultProductsDataProvider,
    private val coroutineScope: CoroutineScope
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        coroutineScope.launch {
            val initialFoodList = productsDataProvider.getDefaultProducts().map { it.toProductEntity() }
            populateFoodDao(initialFoodList)
        }
    }

    private fun populateFoodDao(initialProductList: List<ProductEntity>) {
        val foodDao = productDaoProvider.get()
        foodDao.insertAllFoods(initialProductList)
    }

}