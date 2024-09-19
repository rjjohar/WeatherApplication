package com.example.weatherApplication.ui

import androidx.lifecycle.ViewModel
import com.example.domain.entity.ListEntity
import com.example.domain.entity.WeatherDetailEntity
import com.example.weatherApplication.commonUtils.SingleLiveEvent

class WeatherDetailViewModel : ViewModel() {
    val weatherListDetail = SingleLiveEvent<ListEntity>()
    val city = SingleLiveEvent<String>()

    fun setWeatherDetail(weatherDetailEntity: WeatherDetailEntity) {
        city.value = weatherDetailEntity.city.name
        weatherListDetail.value = weatherDetailEntity.list.first()
    }
}