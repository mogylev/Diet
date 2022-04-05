package com.mohylov.diet.ui.di.components.personalProductComponent

import androidx.lifecycle.ViewModel
import com.mohylov.diet.ui.data.products.ProductsRepository
import com.mohylov.diet.ui.di.ViewModelKey
import com.mohylov.diet.ui.domain.products.FilterStrategy
import com.mohylov.diet.ui.domain.products.personalProducts.PersonalProductsInteractor
import com.mohylov.diet.ui.domain.products.personalProducts.PersonalProductsInteractorImpl
import com.mohylov.diet.ui.domain.products.remove.ProductRemovingInteractor
import com.mohylov.diet.ui.domain.products.remove.ProductRemovingInteractorImpl
import com.mohylov.diet.ui.presentation.productsManagement.personalProducts.PersonalProductsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface PersonalProductsModule {

    @Binds
    fun bindProductRemovingInteractor(
        productRemovingInteractorImpl: ProductRemovingInteractorImpl
    ): ProductRemovingInteractor

    @Binds
    @[IntoMap ViewModelKey(PersonalProductsViewModel::class)]
    fun bindViewModel(viewModel: PersonalProductsViewModel): ViewModel


    companion object {
        @Provides
        fun providePersonalProductsInteractor(
            productsRepository: ProductsRepository,
            filterStrategy: FilterStrategy
        ): PersonalProductsInteractor {
            return PersonalProductsInteractorImpl(
                productsRepository = productsRepository,
                filterStrategy = filterStrategy
            )
        }
    }
}