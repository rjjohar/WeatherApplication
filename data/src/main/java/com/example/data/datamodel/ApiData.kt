package com.example.data.datamodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weatherTable")
data class ApiWeatherData(
    @PrimaryKey val cityName: String,
    val detail: String
)