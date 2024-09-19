package com.example.weatherApplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherApplication.R
import com.example.weatherApplication.databinding.FragmentWeatherHomeBinding
import com.example.weatherApplication.ui.adaptor.HistoryAdaptor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherHomeFragment : Fragment() {

    private var viewBinding: FragmentWeatherHomeBinding? = null
    private val viewModel: WeatherHomeViewModel by viewModels()
    private lateinit var historyAdapter: HistoryAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_weather_home, container, false
        )
        viewBinding?.viewModel = viewModel
        setHistoryDetail()
        initObservers()
        return viewBinding?.root
    }

    private fun initObservers() {
        viewModel.historyDetails.observe(viewLifecycleOwner) { list ->
            historyAdapter.setDetail(list)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            showErrorToast()
        }
        viewModel.weatherDetailResponse.observe(viewLifecycleOwner) { weatherEntity ->
            weatherEntity?.let {
                findNavController().navigate(
                    WeatherHomeFragmentDirections.actionNavToFragmentWeatherDetail(
                        it
                    )
                )
            }
        }
    }

    private fun setHistoryDetail() {
        historyAdapter = HistoryAdaptor()
        viewBinding?.rvHistoryDetail?.adapter = historyAdapter
        historyAdapter.onClickListener = {
            viewModel.fetchWeatherDetails(it)
        }
    }

    private fun showErrorToast() =
        Toast.makeText(requireContext(), R.string.something_went_wrong, Toast.LENGTH_LONG)
            .show()
}