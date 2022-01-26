package com.mohylov.diet.ui.di.components.mainComponent

import androidx.lifecycle.ViewModel
import com.mohylov.diet.ui.data.product.ProductsRepository
import com.mohylov.diet.ui.data.product.ProductsRepositoryImpl
import com.mohylov.diet.ui.di.ViewModelKey
import com.mohylov.diet.ui.domain.products.ProductsInteractor
import com.mohylov.diet.ui.domain.products.ProductsInteractorImpl
import com.mohylov.diet.ui.presentation.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainScreenModule {

    @MainScreenScope
    @Binds
    abstract fun bindFoodsRepository(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository

    @Binds
    abstract fun bindFoodsInteractor(foodsInteractorImpl: ProductsInteractorImpl): ProductsInteractor

    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    abstract fun provideMainViewModel(mainViewModel: MainViewModel) : ViewModel

}