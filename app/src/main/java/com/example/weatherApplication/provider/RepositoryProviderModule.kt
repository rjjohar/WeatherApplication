package com.example.weatherApplication.provider

import com.example.data.api.IWeatherApiService
import com.example.data.db.AppDatabase
import com.example.data.repository.WeatherRepositoryImpl
import com.example.domain.repository.IWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryProviderModule {

    @Provides
    fun getWeatherRepository(apiService: IWeatherApiService, db:AppDatabase ): IWeatherRepository {
        return WeatherRepositoryImpl(apiService,db)
    }
}