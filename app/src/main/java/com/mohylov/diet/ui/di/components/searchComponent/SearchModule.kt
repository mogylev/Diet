package com.mohylov.diet.ui.di.components.searchComponent

import com.mohylov.diet.ui.data.db.AppDatabase
import com.mohylov.diet.ui.data.mealProducts.MealProductDao
import com.mohylov.diet.ui.data.mealProducts.MealProductsRepository
import com.mohylov.diet.ui.data.mealProducts.MealProductsRepositoryImpl
import com.mohylov.diet.ui.data.products.ProductDao
import com.mohylov.diet.ui.data.products.ProductsRepository
import com.mohylov.diet.ui.data.products.ProductsRepositoryImpl
import com.mohylov.diet.ui.domain.mealProductsManagement.MealProductsManagementImpl
import com.mohylov.diet.ui.domain.mealProductsManagement.MealProductsManagementInteractor
import com.mohylov.diet.ui.domain.products.ProductsInteractor
import com.mohylov.diet.ui.domain.products.ProductsInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface SearchModule {

    @Binds
    fun provideProductsRepository(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository

    @Binds
    fun provideMealProductsRepository(mealProductsRepositoryImpl: MealProductsRepositoryImpl): MealProductsRepository

    @Binds
    fun provideProductsInteractor(productsInteractorImpl: ProductsInteractorImpl): ProductsInteractor

    @Binds
    fun provideMealProductsManagerInteractor(mealProductsManagerImpl: MealProductsManagementImpl): MealProductsManagementInteractor

    companion object {

        @Provides
        fun provideProductsDao(appDatabase: AppDatabase): ProductDao {
            return appDatabase.productsDao()
        }

        @Provides
        fun provideMealProductsDao(appDatabase: AppDatabase): MealProductDao {
            return appDatabase.mealProductDao()
        }
    }
}