package com.example.mylocationweather.domain.usecase

import android.location.Location
import com.example.mylocationweather.domain.model.LocationInfo
import com.example.mylocationweather.domain.model.WeatherInfo
import com.example.mylocationweather.domain.service.RemoteDataSource

class GetWeatherUseCase(
    private val remoteDataSource: RemoteDataSource
) {
    fun getWeatherInfo(location: LocationInfo): WeatherInfo {
        return remoteDataSource.getCurrentLocationWeatherInfo(location)
    }
}