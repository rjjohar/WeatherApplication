package com.example.weatherApplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asFlow
import com.example.data.datamodel.CityModel
import com.example.data.repository.WeatherRepositoryImpl
import com.example.domain.common.Resource
import com.example.domain.enum.Status
import com.example.domain.usecase.WeatherUseCase
import com.example.weatherApplication.WeatherDetailViewModelTest.Companion.mockWeatherDetailEntity
import com.example.weatherApplication.ui.WeatherHomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class WeatherHomeViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var weatherRepositoryImpl: WeatherRepositoryImpl

    private lateinit var viewModel: WeatherHomeViewModel
    private lateinit var weatherUseCase: WeatherUseCase
    private val testDispatcher = StandardTestDispatcher()
    private val citiesList = listOf("Noida", "Delhi", "Dubai")


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        // Set the Main dispatcher to a TestDispatcher
        Dispatchers.setMain(testDispatcher)
        weatherUseCase = WeatherUseCase(weatherRepositoryImpl)
        viewModel = WeatherHomeViewModel(weatherUseCase)
        Mockito.`when`(weatherUseCase.fetchHistory()).thenReturn(flowOf(citiesList))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testFetchCities() = runTest(testDispatcher) {
        Mockito.`when`(weatherUseCase.fetchHistory()).thenReturn(flowOf(citiesList))
        viewModel.fetchHistory()
        val data = viewModel.historyDetails.asFlow().first()
        assert(data.first() == citiesList.map { CityModel(name = it) }.first().name)
    }

    @Test
    fun testGetWeatherDetail() = runTest(testDispatcher) {
        val cityName = "Delhi"
        Mockito.`when`(weatherUseCase.fetchWeatherDetails(cityName))
            .thenReturn(flowOf(Resource(Status.SUCCESS,mockWeatherDetailEntity)))
        viewModel.fetchWeatherDetails(cityName)
        val response = viewModel.weatherDetailResponse.asFlow().first()
        assert(response?.city?.name == mockWeatherDetailEntity.city.name)
        assert(viewModel.isDataLoading.value == false)
    }
}