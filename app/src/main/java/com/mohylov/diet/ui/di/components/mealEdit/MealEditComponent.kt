package com.mohylov.diet.ui.di.components.mealEdit

import com.mohylov.diet.ui.data.db.AppDatabase
import com.mohylov.diet.ui.data.mealProducts.MealProductDao
import com.mohylov.diet.ui.data.mealProducts.MealProductsRepository
import com.mohylov.diet.ui.data.mealProducts.MealProductsRepositoryImpl
import com.mohylov.diet.ui.data.product.ProductDao
import com.mohylov.diet.ui.data.product.ProductsRepository
import com.mohylov.diet.ui.data.product.ProductsRepositoryImpl
import com.mohylov.diet.ui.di.AppComponent
import com.mohylov.diet.ui.domain.mealProducts.MealProductsInteractor
import com.mohylov.diet.ui.domain.mealProducts.MealProductsInteractorImpl
import com.mohylov.diet.ui.domain.mealProductsManagement.MealProductsManagementImpl
import com.mohylov.diet.ui.domain.mealProductsManagement.MealProductsManagementInteractor
import com.mohylov.diet.ui.domain.products.ProductsInteractor
import com.mohylov.diet.ui.domain.products.ProductsInteractorImpl
import com.mohylov.diet.ui.presentation.mealEdit.MealEditFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
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

    @Binds
    fun bindProductsRepository(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository

    @Binds
    fun bindMealProductsRepository(mealProductsRepositoryImpl: MealProductsRepositoryImpl): MealProductsRepository

    @Binds
    fun bindProductsInteractor(productsInteractorImpl: ProductsInteractorImpl): ProductsInteractor

    @Binds
    fun bindMealProductsInteractor(mealProductsInteractorImpl: MealProductsInteractorImpl): MealProductsInteractor

    @Binds
    fun bindMealProductsManagementInteractor(mealProductsManagementImpl: MealProductsManagementImpl): MealProductsManagementInteractor

    companion object {

        @Provides
        fun provideMealProductDao(appDatabase: AppDatabase): MealProductDao {
            return appDatabase.mealProductDao()
        }

        @Provides
        fun provideProductDao(appDatabase: AppDatabase): ProductDao {
            return appDatabase.productsDao()
        }
    }

}