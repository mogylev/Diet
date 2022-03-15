package com.mohylov.diet.ui.di.components.mealEdit

import com.mohylov.diet.ui.data.db.AppDatabase
import com.mohylov.diet.ui.data.mealProducts.MealProductDao
import com.mohylov.diet.ui.data.mealProducts.MealProductsRepository
import com.mohylov.diet.ui.data.mealProducts.MealProductsRepositoryImpl
import com.mohylov.diet.ui.data.products.ProductDao
import com.mohylov.diet.ui.data.products.ProductsRepository
import com.mohylov.diet.ui.data.products.ProductsRepositoryImpl
import com.mohylov.diet.ui.di.AppComponent
import com.mohylov.diet.ui.domain.completeMealProduct.CompleteMealProductInteractor
import com.mohylov.diet.ui.domain.completeMealProduct.CompleteMealProductInteractorImpl
import com.mohylov.diet.ui.domain.mealProductsManagement.MealProductsManagementImpl
import com.mohylov.diet.ui.domain.mealProductsManagement.MealProductsManagementInteractor
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
    fun bindMealProductsManagementInteractor(mealProductsManagementImpl: MealProductsManagementImpl): MealProductsManagementInteractor

    @Binds
    fun bindCompleteMealProductInteractor(completeMealProductInteractorImpl: CompleteMealProductInteractorImpl): CompleteMealProductInteractor

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