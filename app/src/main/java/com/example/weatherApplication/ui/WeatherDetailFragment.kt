package com.example.weatherApplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.weatherApplication.R
import com.example.weatherApplication.commonUtils.formatDate
import com.example.weatherApplication.databinding.FragmentWeatherDetailsBinding
import com.example.weatherApplication.ui.adaptor.WeatherDetailAdaptor
import com.example.weatherApplication.ui.model.WeatherDetailUIModel

class WeatherDetailFragment : Fragment() {

    private var viewBinding: FragmentWeatherDetailsBinding? = null
    private val viewModel: WeatherDetailViewModel by viewModels()
    private val args by navArgs<WeatherDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_weather_details, container, false
        )
        viewBinding?.viewModel = viewModel
        viewModel.setWeatherDetail(args.weatherDetailEntity)
        setWeatherDetail()
        initListener()
        return viewBinding?.root
    }

    private fun initListener() {
        viewBinding?.apply {
            bvBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setWeatherDetail() {
        val weatherDetailAdaptor = WeatherDetailAdaptor()
        val list = mutableListOf<WeatherDetailUIModel>()
        val response = args.weatherDetailEntity.list.groupBy { getDayDetail(it.dtTxt) }
        response.onEachIndexed { index, entry ->
            if (index != 0) {
                val detail = entry.value.first()
                list.add(
                    WeatherDetailUIModel(
                        dayDetail = entry.key,
                        weatherIcon = getWeatherIcon(detail.weather.first().mainInfo),
                        lowTemperature = detail.main.tempMin.toString() + "°C",
                        highTemperature = detail.main.tempMax.toString() + "°C",
                        weatherConditions = detail.weather.first().description
                    )
                )
            }
        }
        weatherDetailAdaptor.setList(list)
        viewBinding?.rvWeatherList?.adapter = weatherDetailAdaptor
    }

    private fun getDayDetail(date: String) = date.formatDate().orEmpty()

    private fun getWeatherIcon(info: String): Int {
        return when (info) {
            "Clear" -> {
                R.drawable.day
            }

            "Rain" -> {
                R.drawable.rainy
            }

            "Clouds" -> {
                R.drawable.cloudy
            }

            else -> {
                R.drawable.snowy
            }
        }
    }
}