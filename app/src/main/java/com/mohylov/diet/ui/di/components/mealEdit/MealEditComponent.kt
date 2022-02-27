package com.mohylov.diet.ui.di.components.mealEdit

import com.mohylov.diet.ui.di.AppComponent
import com.mohylov.diet.ui.presentation.mealEdit.MealEditFragment
import dagger.Component
import dagger.Module
import javax.inject.Scope

@MealEditScope
@Component(modules = [MealEditModule::class], dependencies = [AppComponent::class])
interface MealEditComponent {

    fun inject(mealEditFragment: MealEditFragment)

    @Component.Builder
    interface Builder {

        fun deps(appComponent: AppComponent): Builder

        fun build(): MealEditComponent

    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MealEditScope

@Module
interface MealEditModule {

}