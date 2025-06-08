package com.example.mylocationweather.domain.service

import com.example.mylocationweather.data.remote.model.WeatherDto
import com.example.mylocationweather.domain.model.LocationInfo

interface RemoteDataSource {
    suspend fun getCurrentLocationWeatherInfo(location: LocationInfo): WeatherDto
}