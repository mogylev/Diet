package com.mohylov.diet.ui.di.components.personalProductComponent

import com.mohylov.diet.ui.di.AppComponent
import com.mohylov.diet.ui.presentation.productsManagement.personalProducts.PersonalProductsFragment
import dagger.Component
import javax.inject.Scope

@PersonalProductsScope
@Component(modules = [PersonalProductsModule::class], dependencies = [AppComponent::class])
interface PersonalProductsComponent {

    fun inject(fragment: PersonalProductsFragment)

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): PersonalProductsComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PersonalProductsScope