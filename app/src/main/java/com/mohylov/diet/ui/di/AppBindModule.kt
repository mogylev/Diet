package com.mohylov.diet.ui.di

import androidx.room.RoomDatabase
import com.mohylov.diet.ui.data.product.productDataProvider.InitialProductsDataProvider
import com.mohylov.diet.ui.data.product.productDataProvider.InitialLocalProductsDataProviderImpl
import com.mohylov.diet.ui.data.db.PrePopulateCallback
import dagger.Binds
import dagger.Module


@Module
interface AppBindModule {

    @Binds
    fun bindDatabaseCallback(callback: PrePopulateCallback): RoomDatabase.Callback

    @Binds
    fun bindInitialDataProvider(initialProvider: InitialLocalProductsDataProviderImpl): InitialProductsDataProvider
}