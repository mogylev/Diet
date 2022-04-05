package com.mohylov.diet.ui.di

import android.content.Context
import com.mohylov.diet.ui.data.Persistence
import com.mohylov.diet.ui.data.db.AppDatabase
import com.mohylov.diet.ui.data.filters.FiltersRepository
import com.mohylov.diet.ui.data.products.ProductsRepository
import com.mohylov.diet.ui.domain.products.FilterStrategy
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineScope
import javax.inject.Qualifier
import javax.inject.Scope
import javax.inject.Singleton

@Application
@Component(modules = [AppModule::class, DataBaseModule::class])
interface AppComponent {

    val appDatabase: AppDatabase

    val persistence: Persistence

    val filterRepository: FiltersRepository

    val productsRepository: ProductsRepository

    val appCoroutineScope: CoroutineScope

    val filterStrategy: FilterStrategy

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class Application