package com.example.mylocationweather.domain.service

import com.example.mylocationweather.data.remote.model.weatherModel.WeatherDto
import com.example.mylocationweather.data.remote.model.LocationInfo

interface RemoteDataSource {
    suspend fun getCurrentLocationWeatherInfo(location: LocationInfo): WeatherDto
}