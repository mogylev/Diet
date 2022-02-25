package com.mohylov.diet.di.components.mainComponent

import com.mohylov.diet.di.AppComponent
import com.mohylov.diet.presentation.main.MainScreenFragment
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