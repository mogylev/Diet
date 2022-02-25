package com.mohylov.diet.di


import com.mohylov.diet.di.AppBindModule
import dagger.Module
import dagger.Provides

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module(includes = [AppBindModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

}

