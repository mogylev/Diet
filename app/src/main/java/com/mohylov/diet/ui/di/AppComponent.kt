package com.mohylov.diet.ui.di

import android.content.Context
import com.mohylov.diet.ui.data.db.AppDatabase
import com.mohylov.diet.ui.data.product.ProductDao
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Component(modules = [AppModule::class, DataBaseModule::class])
@Singleton
interface AppComponent {

    val appDatabase: AppDatabase

    val appCoroutineScope: CoroutineScope

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

}