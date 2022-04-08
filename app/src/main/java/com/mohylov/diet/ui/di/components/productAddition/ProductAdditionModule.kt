package com.mohylov.diet.ui.di.components.productAddition

import androidx.lifecycle.ViewModel
import com.mohylov.diet.ui.data.db.AppDatabase
import com.mohylov.diet.ui.di.ViewModelKey
import com.mohylov.diet.ui.domain.products.ProductsInteractor
import com.mohylov.diet.ui.domain.products.ProductsInteractorImpl
import com.mohylov.diet.ui.domain.products.create.ProductCreationInteractor
import com.mohylov.diet.ui.domain.products.create.ProductCreationInteractorImpl
import com.mohylov.diet.ui.presentation.productAddition.ProductAdditionViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface ProductAdditionModule {

    @Binds
    fun bindProductInteractor(productsInteractorImp: ProductsInteractorImpl): ProductsInteractor

    @Binds
    fun bindProductCreationInteractor(productCreationInteractorImpl: ProductCreationInteractorImpl): ProductCreationInteractor

    @Binds
    @[IntoMap ViewModelKey(ProductAdditionViewModel::class)]
    fun bindProductAdditionViewModel(viewModel: ProductAdditionViewModel): ViewModel

    companion object {
        @Provides
        fun provideProductsDao(appDatabase: AppDatabase) = appDatabase.productsDao()
    }

}