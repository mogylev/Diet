package com.mohylov.diet.ui.di

import androidx.room.RoomDatabase
import com.mohylov.diet.ui.data.products.productDataProvider.DefaultProductsDataProvider
import com.mohylov.diet.ui.data.products.productDataProvider.DefaultLocalProductsDataProviderImpl
import com.mohylov.diet.ui.data.db.PrePopulateCallback
import com.mohylov.diet.ui.domain.products.DefaultFilterStrategy
import com.mohylov.diet.ui.domain.products.FilterStrategy
import dagger.Binds
import dagger.Module


@Module
interface AppBindModule {

    @Binds
    fun bindDatabaseCallback(callback: PrePopulateCallback): RoomDatabase.Callback

    @Binds
    fun bindInitialDataProvider(initialProvider: DefaultLocalProductsDataProviderImpl): DefaultProductsDataProvider

    @Binds
    abstract fun bindFilterStrategy(defaultFilterStrategy: DefaultFilterStrategy): FilterStrategy
}