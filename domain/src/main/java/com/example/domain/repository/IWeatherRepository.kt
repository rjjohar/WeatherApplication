package com.example.domain.repository

import com.example.domain.common.Resource
import com.example.domain.entity.WeatherDetailEntity
import kotlinx.coroutines.flow.Flow

interface IWeatherRepository {
    fun fetchWeatherDetails(name: String): Flow<Resource<WeatherDetailEntity>>

    fun fetchHistory(): Flow<List<String>>

    fun removeDetails() :Flow<Unit>
}