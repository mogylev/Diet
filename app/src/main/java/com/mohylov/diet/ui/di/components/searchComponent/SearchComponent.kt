package com.mohylov.diet.ui.di.components.searchComponent

import com.mohylov.diet.ui.di.AppComponent
import com.mohylov.diet.ui.presentation.search.SearchFragment
import dagger.Component

@SearchScope
@Component(modules = [SearchModule::class], dependencies = [AppComponent::class])
interface SearchComponent {

    fun inject(searchFragment: SearchFragment)

    @Component.Builder
    interface Builder {

        fun deps(appComponent: AppComponent): Builder

        fun build(): SearchComponent
    }
}