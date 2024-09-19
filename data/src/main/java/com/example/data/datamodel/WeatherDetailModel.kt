package com.example.data.datamodel

import com.google.gson.annotations.SerializedName

data class WeatherDetailModel(
    @SerializedName("list") val list: List<ListModel>,
    @SerializedName("city") val city: CityModel
)

data class ListModel(
    @SerializedName("main") val main: MainModel,
    @SerializedName("weather") val weather: List<WeatherModel>,
    @SerializedName("wind") val wind: WindModel,
    @SerializedName("dt_txt") val dtTxt: String
)

data class CityModel(
    @SerializedName("name") val name: String,
)

data class MainModel(
    @SerializedName("temp") val temp: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("humidity") val humidity: Int
)

data class WeatherModel(
    @SerializedName("main") val mainInfo: String,
    @SerializedName("description") val description: String
)

data class WindModel(
    @SerializedName("speed") val speed: Double
)
