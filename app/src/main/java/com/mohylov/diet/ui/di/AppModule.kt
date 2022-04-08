package com.mohylov.diet.ui.di


import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.mohylov.diet.ui.data.DataStorePersistence
import com.mohylov.diet.ui.data.Persistence
import com.mohylov.diet.ui.data.filters.FiltersRepository
import com.mohylov.diet.ui.data.filters.FiltersRepositoryImpl
import com.mohylov.diet.ui.data.products.ProductsRepository
import com.mohylov.diet.ui.data.products.ProductsRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import java.io.File
import javax.inject.Singleton

@Module(includes = [AppBindModule::class])
class AppModule {

    @Application
    @Provides
    fun provideAppCoroutineScope() = CoroutineScope(SupervisorJob())

    @Application
    @Provides
    fun providePersistence(): Persistence {
        return DataStorePersistence(PreferenceDataStoreFactory.create(produceFile = {
            File("app_preferences")
        }))
    }

    @Application
    @Provides
    fun provideFilterRepository(filtersRepositoryImpl: FiltersRepositoryImpl): FiltersRepository {
        return filtersRepositoryImpl
    }

    @Application
    @Provides
    fun provideProductsRepository(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository {
        return productsRepositoryImpl
    }

}

