package com.mohylov.diet.ui.di

import android.content.Context
import com.mohylov.diet.ui.data.Persistence
import com.mohylov.diet.ui.data.db.AppDatabase
import com.mohylov.diet.ui.data.filters.FiltersRepository
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Component(modules = [AppModule::class, DataBaseModule::class])
@Singleton
interface AppComponent {

    val appDatabase: AppDatabase

    val persistence: Persistence

    val filterRepository: FiltersRepository

    val appCoroutineScope: CoroutineScope

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

}