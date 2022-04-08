package com.mohylov.diet.ui.di.components.productAddition

import com.mohylov.diet.ui.di.AppComponent
import com.mohylov.diet.ui.presentation.productAddition.ProductAdditionFragment
import dagger.Component

@ProductAdditionScope
@Component(modules = [ProductAdditionModule::class], dependencies = [AppComponent::class])
interface ProductAdditionComponent {

    fun inject(productAdditionFragment: ProductAdditionFragment)

    @Component.Builder
    interface Builder {

        fun deps(appComponent: AppComponent): Builder

        fun build(): ProductAdditionComponent
    }

}