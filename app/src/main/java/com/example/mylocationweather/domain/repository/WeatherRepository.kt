package com.example.mylocationweather.domain.repository

import com.example.mylocationweather.domain.model.LocationInfo
import com.example.mylocationweather.domain.model.WeatherInfo

interface WeatherRepository {

    fun getCurrentLocationWeatherInfo(location: LocationInfo): WeatherInfo
}