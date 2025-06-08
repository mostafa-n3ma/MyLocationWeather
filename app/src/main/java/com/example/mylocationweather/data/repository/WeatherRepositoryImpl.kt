package com.example.mylocationweather.data.repository

import com.example.mylocationweather.domain.model.LocationInfo
import com.example.mylocationweather.domain.model.WeatherInfo
import com.example.mylocationweather.domain.repository.WeatherRepository

class WeatherRepositoryImpl: WeatherRepository {
    override fun getCurrentLocationWeatherInfo(location: LocationInfo): WeatherInfo {
        TODO("Not yet implemented")
    }
}