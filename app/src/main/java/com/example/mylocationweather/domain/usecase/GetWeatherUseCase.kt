package com.example.mylocationweather.domain.usecase

import com.example.mylocationweather.domain.model.LocationInfo
import com.example.mylocationweather.domain.model.WeatherEntity
import com.example.mylocationweather.domain.repository.WeatherRepository

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend fun getWeatherInfo(location: LocationInfo): WeatherEntity {
        return weatherRepository.getCurrentLocationWeatherInfo(location)
    }
}