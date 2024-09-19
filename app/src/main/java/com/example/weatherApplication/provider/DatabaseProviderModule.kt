package com.example.weatherApplication.provider

import android.app.Application
import androidx.room.Room
import com.example.data.WEATHER_DETAIL_DB
import com.example.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseProviderModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, WEATHER_DETAIL_DB)
            .build()
}