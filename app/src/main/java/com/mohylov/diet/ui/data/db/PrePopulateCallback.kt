package com.mohylov.diet.ui.data.db

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mohylov.diet.ui.data.product.entities.ProductEntity
import com.mohylov.diet.ui.data.product.productDataProvider.InitialProductsDataProvider
import com.mohylov.diet.ui.di.AppCoroutineScope
import com.mohylov.diet.ui.domain.toFoodEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider


class PrePopulateCallback @Inject constructor(
    private val productDaoProvider: Provider<ProductDao>,
    private val productsDataProvider: InitialProductsDataProvider,
    @AppCoroutineScope private val coroutineScope: CoroutineScope
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        coroutineScope.launch {
            val initialFoodList = productsDataProvider.provideFoodData().map { it.toFoodEntity() }
            populateFoodDao(initialFoodList)
        }
    }

    private fun populateFoodDao(initialProductList: List<ProductEntity>) {
        val foodDao = productDaoProvider.get()
        foodDao.insertAllFoods(initialProductList)
    }

}