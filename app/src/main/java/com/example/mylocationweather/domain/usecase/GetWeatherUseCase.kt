package com.example.mylocationweather.domain.usecase

import com.example.mylocationweather.data.remote.model.LocationInfo
import com.example.mylocationweather.domain.model.WeatherEntity
import com.example.mylocationweather.domain.repository.LocationRepository
import com.example.mylocationweather.domain.repository.WeatherRepository

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
) {
    suspend fun getWeatherInfo(): WeatherEntity {
        val locationInfo = locationRepository.getCurrentLocation()
        return weatherRepository.getCurrentLocationWeatherInfo(locationInfo)
    }
}