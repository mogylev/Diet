package com.mohylov.diet.ui.di.components.defaultProductsComponent

import com.mohylov.diet.ui.di.AppComponent
import com.mohylov.diet.ui.presentation.productsManagement.defaultProducts.DefaultProductsFragment
import dagger.Component

@DefaultProductsScope
@Component(modules = [DefaultProductsModule::class], dependencies = [AppComponent::class])
interface DefaultProductsComponent {

    fun inject(fragment: DefaultProductsFragment)


    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): DefaultProductsComponent
    }

}