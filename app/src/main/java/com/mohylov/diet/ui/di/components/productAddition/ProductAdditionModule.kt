package com.mohylov.diet.ui.di.components.productAddition

import androidx.lifecycle.ViewModel
import com.mohylov.diet.ui.data.db.AppDatabase
import com.mohylov.diet.ui.data.product.ProductsRepository
import com.mohylov.diet.ui.data.product.ProductsRepositoryImpl
import com.mohylov.diet.ui.di.ViewModelKey
import com.mohylov.diet.ui.domain.products.ProductsInteractor
import com.mohylov.diet.ui.domain.products.ProductsInteractorImpl
import com.mohylov.diet.ui.presentation.productAddition.ProductAdditionViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface ProductAdditionModule {

    @Binds
    fun provideProductRepository(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository

    @Binds
    fun provideProductInteractor(productsInteractorImp: ProductsInteractorImpl): ProductsInteractor

    @Binds
    @[IntoMap ViewModelKey(ProductAdditionViewModel::class)]
    fun bindProductAdditionViewModel(viewModel: ProductAdditionViewModel): ViewModel

    companion object {
        @Provides
        fun provideProductsDao(appDatabase: AppDatabase) = appDatabase.productsDao()
    }

}