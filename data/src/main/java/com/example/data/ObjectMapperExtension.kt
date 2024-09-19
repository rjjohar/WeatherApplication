package com.example.data

import com.example.data.datamodel.CityModel
import com.example.data.datamodel.ListModel
import com.example.data.datamodel.MainModel
import com.example.data.datamodel.WeatherDetailModel
import com.example.data.datamodel.WeatherModel
import com.example.data.datamodel.WindModel
import com.example.domain.entity.CityEntity
import com.example.domain.entity.ListEntity
import com.example.domain.entity.MainEntity
import com.example.domain.entity.WeatherDetailEntity
import com.example.domain.entity.WeatherEntity
import com.example.domain.entity.WindEntity

fun WeatherDetailModel.mapToEntity(): WeatherDetailEntity {
    return WeatherDetailEntity(
        list = list.map { it.toListEntity() },
        city = city.toCityEntity()
    )
}

fun ListModel.toListEntity() =
    ListEntity(
        main = main.toMainEntity(),
        weather = weather.map { it.toWeatherEntity() },
        wind = wind.toWindEntity(),
        dtTxt = dtTxt
    )

fun MainModel.toMainEntity() =
    MainEntity(temp = temp, tempMin = tempMin, tempMax = tempMax, humidity = humidity)

fun WeatherModel.toWeatherEntity() = WeatherEntity(mainInfo = mainInfo, description = description)

fun WindModel.toWindEntity() = WindEntity(speed = speed)

fun CityModel.toCityEntity(): CityEntity = CityEntity(name = name)

