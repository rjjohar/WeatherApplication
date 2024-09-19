package com.example.weatherApplication.ui.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherApplication.R
import com.example.weatherApplication.databinding.ItemWeatherDetailBinding
import com.example.weatherApplication.ui.model.WeatherDetailUIModel

class WeatherDetailAdaptor : RecyclerView.Adapter<WeatherDetailAdaptor.ViewHolder>() {
    private var list: List<WeatherDetailUIModel>? = null

    class ViewHolder(private val binding: ItemWeatherDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: WeatherDetailUIModel) {
            binding.uiModel = model
            binding.ivWeather.setImageResource(model.weatherIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemWeatherDetailBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_weather_detail,
            parent,
            false
        );
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    fun setList(weatherList: List<WeatherDetailUIModel>) {
        this.list = weatherList
        notifyDataSetChanged()
    }

}