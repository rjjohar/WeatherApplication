package com.example.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherDetailEntity(
    val list: List<ListEntity>,
    val city: CityEntity
) : Parcelable

@Parcelize
data class ListEntity(
    val main: MainEntity,
    val weather: List<WeatherEntity>,
    val wind: WindEntity,
    val dtTxt: String
) : Parcelable

@Parcelize
data class CityEntity(
    val name: String
) : Parcelable

@Parcelize
data class MainEntity(
    val temp: Double,
    val tempMin: Double,
    val tempMax: Double,
    val humidity: Int
) : Parcelable

@Parcelize
data class WeatherEntity(
    val mainInfo: String,
    val description: String
) : Parcelable

@Parcelize
data class WindEntity(
    val speed: Double
) : Parcelable
