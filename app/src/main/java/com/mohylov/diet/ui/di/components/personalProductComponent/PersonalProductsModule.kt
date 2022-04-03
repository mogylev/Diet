package com.mohylov.diet.ui.di.components.personalProductComponent

import androidx.lifecycle.ViewModel
import com.mohylov.diet.ui.di.ViewModelKey
import com.mohylov.diet.ui.domain.products.personal.PersonalProductsInteractor
import com.mohylov.diet.ui.domain.products.personal.PersonalProductsInteractorImpl
import com.mohylov.diet.ui.domain.products.remove.ProductRemovingInteractor
import com.mohylov.diet.ui.domain.products.remove.ProductRemovingInteractorImpl
import com.mohylov.diet.ui.presentation.productsManagement.personal.PersonalProductsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PersonalProductsModule {

    @Binds
    abstract fun bindPersonalProductsInteractor(
        personalProductsInteractorImpl: PersonalProductsInteractorImpl
    ): PersonalProductsInteractor

    @Binds
    abstract fun bindProductRemovingInteractor(
        productRemovingInteractorImpl: ProductRemovingInteractorImpl
    ): ProductRemovingInteractor

    @Binds
    @[IntoMap ViewModelKey(PersonalProductsViewModel::class)]
    abstract fun bindViewModel(viewModel: PersonalProductsViewModel): ViewModel

}