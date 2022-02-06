package com.mohylov.diet.ui.di.components

import android.content.Context
import com.mohylov.diet.ui.data.db.ProductDao
import com.mohylov.diet.ui.di.AppModule
import com.mohylov.diet.ui.di.DataBaseModule
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Component(modules = [AppModule::class, DataBaseModule::class])
@Singleton
interface AppComponent {

    val productDao: ProductDao

    val appCoroutineScope: CoroutineScope

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

}