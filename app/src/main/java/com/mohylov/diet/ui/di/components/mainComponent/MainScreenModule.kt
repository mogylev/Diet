package com.mohylov.diet.ui.di.components.mainComponent

import androidx.lifecycle.ViewModel
import com.mohylov.diet.ui.data.db.AppDatabase
import com.mohylov.diet.ui.data.mealProducts.MealProductDao
import com.mohylov.diet.ui.data.mealProducts.MealProductsRepository
import com.mohylov.diet.ui.data.mealProducts.MealProductsRepositoryImpl
import com.mohylov.diet.ui.di.ViewModelKey
import com.mohylov.diet.ui.domain.mealProducts.MealProductsInteractor
import com.mohylov.diet.ui.domain.mealProducts.MealProductsInteractorImpl
import com.mohylov.diet.ui.domain.mealProductsCalculator.MealProductCalculateInteractor
import com.mohylov.diet.ui.domain.mealProductsCalculator.MealProductsCalculatorInteractorImpl
import com.mohylov.diet.ui.domain.mealProductsManagement.MealProductsManagementInteractor
import com.mohylov.diet.ui.domain.mealProductsManagement.MealProductsManagementImpl
import com.mohylov.diet.ui.presentation.main.MainViewModel
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