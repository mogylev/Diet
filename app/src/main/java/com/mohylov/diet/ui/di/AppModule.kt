package com.mohylov.diet.ui.di


import dagger.Module
import dagger.Provides

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [AppBindModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

}

