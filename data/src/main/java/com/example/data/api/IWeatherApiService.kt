package com.example.data.api

import com.example.data.WEATHER_URL
import com.example.data.datamodel.WeatherDetailModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherApiService {
    @GET(WEATHER_URL)
    suspend fun fetchWeatherDetails(
        @Query("q") name: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Response<WeatherDetailModel>
}