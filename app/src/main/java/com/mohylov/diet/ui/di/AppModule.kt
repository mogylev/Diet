package com.mohylov.diet.ui.di



import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.mohylov.diet.ui.data.DataStorePersistence
import com.mohylov.diet.ui.data.Persistence
import com.mohylov.diet.ui.data.filters.FiltersRepository
import com.mohylov.diet.ui.data.filters.FiltersRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import java.io.File
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

    @Singleton
    @Provides
    fun provideFilterRepository(filtersRepositoryImpl: FiltersRepositoryImpl): FiltersRepository {
        return filtersRepositoryImpl
    }

}

