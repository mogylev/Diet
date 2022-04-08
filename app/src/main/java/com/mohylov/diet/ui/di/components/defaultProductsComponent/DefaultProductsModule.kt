package com.mohylov.diet.ui.di.components.defaultProductsComponent

import androidx.lifecycle.ViewModel
import com.mohylov.diet.ui.data.products.ProductsRepository
import com.mohylov.diet.ui.di.ViewModelKey
import com.mohylov.diet.ui.domain.products.FilterStrategy
import com.mohylov.diet.ui.domain.products.defaultProducts.DefaultProductsInteractor
import com.mohylov.diet.ui.domain.products.defaultProducts.DefaultProductsInteractorImpl
import com.mohylov.diet.ui.presentation.productsManagement.defaultProducts.DefaultProductsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface DefaultProductsModule {

    @Binds
    @[IntoMap ViewModelKey(DefaultProductsViewModel::class)]
    fun bindViewModel(defaultProductsViewModel: DefaultProductsViewModel): ViewModel

    companion object {

        @Provides
        fun provideDefaultProductsInteractor(
            productsRepository: ProductsRepository,
            filterStrategy: FilterStrategy
        ): DefaultProductsInteractor {
            return DefaultProductsInteractorImpl(productsRepository, filterStrategy)
        }
    }
}