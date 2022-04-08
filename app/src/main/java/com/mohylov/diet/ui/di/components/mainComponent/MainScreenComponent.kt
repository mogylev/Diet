package com.mohylov.diet.ui.di.components.mainComponent

import com.mohylov.diet.ui.di.AppComponent
import com.mohylov.diet.ui.presentation.mealsList.MealsListFragment
import dagger.Component


@MainScreenScope
@Component(modules = [MainScreenModule::class], dependencies = [AppComponent::class])
interface MainScreenComponent {

    fun inject(mealsListFragment: MealsListFragment)

    @Component.Builder
    interface Builder {

        fun deps(appComponent: AppComponent): Builder

        fun build(): MainScreenComponent
    }

}