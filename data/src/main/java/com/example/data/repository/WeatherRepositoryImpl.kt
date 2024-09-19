package com.example.data.repository

import com.example.data.API_KEY
import com.example.data.api.IWeatherApiService
import com.example.data.datamodel.ApiWeatherData
import com.example.data.datamodel.WeatherDetailModel
import com.example.data.db.AppDatabase
import com.example.data.mapToEntity
import com.example.domain.common.Entity
import com.example.domain.common.Resource
import com.example.domain.entity.WeatherDetailEntity
import com.example.domain.enum.Status
import com.example.domain.repository.IWeatherRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl(private val apiService: IWeatherApiService, private val db: AppDatabase) :
    IWeatherRepository {

    override fun fetchWeatherDetails(name: String): Flow<Resource<WeatherDetailEntity>> {
        return flow {
            try {
                val dao = db.weatherDao()
                val cacheData = dao.getApiData(name)
                if (cacheData != null) {
                    val weatherForecastModel =
                        Gson().fromJson(cacheData.detail, WeatherDetailModel::class.java)
                    emit(Resource(Status.SUCCESS, weatherForecastModel.mapToEntity()))
                } else {
                    val response = apiService.fetchWeatherDetails(name, API_KEY, "metric")
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body == null) {
                            emit(Resource(Status.ERROR))
                        } else {
                            dao.insert(ApiWeatherData(name, Gson().toJson(body)))
                            emit(Resource(Status.SUCCESS, body.mapToEntity()))
                        }
                    } else {
                        emit(
                            Resource(
                                Status.ERROR,
                                null,
                                Entity.Error(response.code(), response.message())
                            )
                        )
                    }
                }
            } catch (exception: Exception) {
                emit(Resource(Status.ERROR, null, Entity.Error(1, exception.message.orEmpty())))
            }
        }
    }

    override fun fetchHistory(): Flow<List<String>> {
        return flow {
            emit(db.weatherDao().getCities())
        }
    }

    override fun removeDetails(): Flow<Unit> {
        return flow {
            emit(db.weatherDao().deleteAllCities())
        }
    }
}