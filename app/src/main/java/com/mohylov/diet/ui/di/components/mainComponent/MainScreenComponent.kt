package com.mohylov.diet.ui.di.components.mainComponent

import com.mohylov.diet.ui.di.components.AppComponent
import com.mohylov.diet.ui.presentation.main.MainScreenFragment
import dagger.Component


@MainScreenScope
@Component(modules = [MainScreenModule::class], dependencies = [AppComponent::class])
interface MainScreenComponent {

    fun inject(mainScreenFragment: MainScreenFragment)

    @Component.Builder
    interface Builder {

        fun deps(appComponent: AppComponent): Builder

        fun build(): MainScreenComponent
    }

}