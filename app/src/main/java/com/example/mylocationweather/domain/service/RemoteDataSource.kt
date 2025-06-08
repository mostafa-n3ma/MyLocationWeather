package com.example.mylocationweather.domain.service

import com.example.mylocationweather.domain.model.LocationInfo
import com.example.mylocationweather.domain.model.WeatherInfo

interface RemoteDataSource {
    suspend fun getCurrentLocationWeatherInfo(location: LocationInfo): WeatherInfo
}