package com.example.domain.usecase

import com.example.domain.common.Resource
import com.example.domain.entity.WeatherDetailEntity
import com.example.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow

class WeatherUseCase(private val weatherRepository: IWeatherRepository) {
    fun fetchWeatherDetails(
        name: String,
    ): Flow<Resource<WeatherDetailEntity>> {
        return weatherRepository.fetchWeatherDetails(name)
    }

    fun fetchHistory(): Flow<List<String>> {
        return weatherRepository.fetchHistory()
    }

    fun removeDetails(): Flow<Unit> {
        return weatherRepository.removeDetails()
    }

}