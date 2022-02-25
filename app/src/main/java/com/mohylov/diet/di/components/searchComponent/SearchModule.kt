package com.mohylov.diet.di.components.searchComponent

import com.mohylov.diet.data.db.AppDatabase
import com.mohylov.diet.data.mealProducts.MealProductDao
import com.mohylov.diet.data.mealProducts.MealProductsRepository
import com.mohylov.diet.data.mealProducts.MealProductsRepositoryImpl
import com.mohylov.diet.data.product.ProductDao
import com.mohylov.diet.data.product.ProductsRepository
import com.mohylov.diet.data.product.ProductsRepositoryImpl
import com.mohylov.diet.domain.mealProductsManagement.MealProductsManagementImpl
import com.mohylov.diet.domain.mealProductsManagement.MealProductsManagementInteractor
import com.mohylov.diet.domain.products.ProductsInteractor
import com.mohylov.diet.domain.products.ProductsInteractorImpl
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