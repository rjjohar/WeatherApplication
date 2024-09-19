package com.example.weatherApplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asFlow
import com.example.domain.entity.CityEntity
import com.example.domain.entity.ListEntity
import com.example.domain.entity.MainEntity
import com.example.domain.entity.WeatherDetailEntity
import com.example.domain.entity.WeatherEntity
import com.example.domain.entity.WindEntity
import com.example.weatherApplication.ui.WeatherDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class WeatherDetailViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: WeatherDetailViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = WeatherDetailViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testSetData() = runTest(testDispatcher) {
        viewModel.setWeatherDetail(mockWeatherDetailEntity)
        assert(viewModel.city.value == "Noida")
        val response = viewModel.weatherListDetail.asFlow().first()
        assert(response?.wind?.speed == mockWeatherDetailEntity.list.first().wind.speed)
    }

    companion object {
        val mockWeatherDetailEntity = WeatherDetailEntity(
            list = listOf(
                ListEntity(
                    main = MainEntity(
                        temp = 299.31, tempMin = 299.31, tempMax = 299.35, humidity = 80
                    ), wind = WindEntity(speed = 15.5), weather = listOf(
                        WeatherEntity(
                            mainInfo = "Clear", description = "clear sky"
                        )
                    ), dtTxt = "2024-09-19 11:00:00"

                )
            ), city = CityEntity("Noida")
        )
    }
}