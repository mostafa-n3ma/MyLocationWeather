package com.example.mylocationweather.domain.repository

import com.example.mylocationweather.domain.model.LocationInfo
import com.example.mylocationweather.domain.model.WeatherEntity

interface WeatherRepository {

    suspend fun getCurrentLocationWeatherInfo(location: LocationInfo): WeatherEntity
}