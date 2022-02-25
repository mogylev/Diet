package com.mohylov.diet.di.components.mainComponent

import androidx.lifecycle.ViewModel
import com.mohylov.diet.data.db.AppDatabase
import com.mohylov.diet.data.mealProducts.MealProductDao
import com.mohylov.diet.data.mealProducts.MealProductsRepository
import com.mohylov.diet.data.mealProducts.MealProductsRepositoryImpl
import com.mohylov.diet.di.ViewModelKey
import com.mohylov.diet.domain.mealProducts.MealProductsInteractor
import com.mohylov.diet.domain.mealProducts.MealProductsInteractorImpl
import com.mohylov.diet.domain.mealProductsCalculator.MealProductCalculateInteractor
import com.mohylov.diet.domain.mealProductsCalculator.MealProductsCalculatorInteractorImpl
import com.mohylov.diet.domain.mealProductsManagement.MealProductsManagementInteractor
import com.mohylov.diet.domain.mealProductsManagement.MealProductsManagementImpl
import com.mohylov.diet.presentation.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface MainScreenModule {

    @Binds
    fun provideMealProductsRepository(mealProductsRepositoryImpl: MealProductsRepositoryImpl): MealProductsRepository

    @Binds
    fun provideMealProductsInteractor(mealProductsInteractorImpl: MealProductsInteractorImpl): MealProductsInteractor

    @Binds
    fun provideMealProductsManagerInteractor(mealProductsManagerImpl: MealProductsManagementImpl): MealProductsManagementInteractor

    @Binds
    fun bindMealProductsCalculatorInteractor(
        mealProductsCalculatorInteractorImpl: MealProductsCalculatorInteractorImpl
    ): MealProductCalculateInteractor

    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    companion object {

        @Provides
        fun provideMealProductsDao(appDatabase: AppDatabase): MealProductDao {
            return appDatabase.mealProductDao()
        }
    }

}