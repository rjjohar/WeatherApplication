package com.example.weatherApplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.WeatherDetailEntity
import com.example.domain.usecase.WeatherUseCase
import com.example.weatherApplication.commonUtils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherHomeViewModel @Inject constructor(private val weatherUseCase: WeatherUseCase) :
    ViewModel() {

    val weatherDetailResponse = SingleLiveEvent<WeatherDetailEntity?>()
    val error = SingleLiveEvent<String>()
    val historyDetails = SingleLiveEvent<List<String>>()
    val isDataLoading = MutableLiveData(false)
    val name = MutableLiveData<String>()

    fun fetchWeatherDetails(name: String) {
        viewModelScope.launch {
            withContext(IO) {
                weatherUseCase.fetchWeatherDetails(name).collect { response ->
                    isDataLoading.postValue(response.isLoading())
                    response.onSuccess {
                        weatherDetailResponse.postValue(response.data)
                    }.onError {
                        error.postValue(response.error?.errorMessage)
                    }
                }
            }
        }
    }

    fun fetchHistory() {
        viewModelScope.launch {
            withContext(IO) {
                weatherUseCase.fetchHistory().collect { list ->
                    historyDetails.postValue(list.map { it })
                }
            }
        }
    }

    fun removeDetails() {
        viewModelScope.launch {
            withContext(IO) {
                weatherUseCase.removeDetails().collect {
                    fetchHistory()
                }
            }
        }
    }
}