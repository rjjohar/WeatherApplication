package com.example.weatherApplication.ui.model

data class WeatherDetailUIModel(
    val dayDetail: String,
    val weatherIcon: Int,
    val highTemperature: String,
    val lowTemperature: String,
    val weatherConditions: String
)
