package com.mohylov.diet.ui.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mohylov.diet.ui.data.db.AppDatabase
import com.mohylov.diet.ui.data.db.ProductDao
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
 class DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        appContext: Context,
        databaseCallBack: RoomDatabase.Callback
    ): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "food_database")
            .fallbackToDestructiveMigration()
            .addCallback(databaseCallBack)
            .build()
    }

    @Provides
    fun provideFoodDao(appDatabase: AppDatabase) : ProductDao {
        return appDatabase.foodDao()
    }
}