package com.example.mylocationweather.domain.service

import com.example.mylocationweather.domain.model.LocationInfo
import com.example.mylocationweather.domain.model.WeatherInfo

interface RemoteDataSource {
    fun getCurrentLocationWeatherInfo(location: LocationInfo): WeatherInfo
}