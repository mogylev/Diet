package com.mohylov.diet.ui.di


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.mohylov.diet.ui.data.DataStorePersistence
import com.mohylov.diet.ui.data.Persistence
import dagger.Module
import dagger.Provides

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import java.io.File
import java.util.prefs.Preferences
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [AppBindModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Singleton
    @Provides
    fun providePersistence(): Persistence {
        return DataStorePersistence(PreferenceDataStoreFactory.create(produceFile = {
            File("app_preferences")
        }))
    }

}

