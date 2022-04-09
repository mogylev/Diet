package com.mohylov.diet.ui.di.components.mealsListComponent

import com.mohylov.diet.ui.di.AppComponent
import com.mohylov.diet.ui.presentation.mealsList.MealsListFragment
import dagger.Component


@MealsListScreenScope
@Component(modules = [MealsListScreenModule::class], dependencies = [AppComponent::class])
interface MealsListScreenComponent {

    fun inject(mealsListFragment: MealsListFragment)

    @Component.Builder
    interface Builder {

        fun deps(appComponent: AppComponent): Builder

        fun build(): MealsListScreenComponent
    }

}