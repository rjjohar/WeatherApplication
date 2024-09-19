package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.datamodel.ApiWeatherData

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(apiWeatherData: ApiWeatherData)

    @Query("SELECT * FROM weatherTable WHERE cityName = :cityName")
    suspend fun getApiData(cityName: String): ApiWeatherData?

    @Query("SELECT cityName FROM weatherTable")
    suspend fun getCities(): List<String>

    @Query("DELETE FROM weatherTable")
    suspend fun deleteAllCities()
}