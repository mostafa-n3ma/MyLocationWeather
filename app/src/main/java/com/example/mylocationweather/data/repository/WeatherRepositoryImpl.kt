package com.example.mylocationweather.data.repository

import com.example.mylocationweather.data.remote.model.mapToEntity
import com.example.mylocationweather.domain.model.LocationInfo
import com.example.mylocationweather.domain.model.WeatherEntity
import com.example.mylocationweather.domain.repository.WeatherRepository
import com.example.mylocationweather.domain.service.RemoteDataSource

class WeatherRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): WeatherRepository {
    override suspend fun getCurrentLocationWeatherInfo(location: LocationInfo): WeatherEntity {
        val currentWeatherDto =  remoteDataSource.getCurrentLocationWeatherInfo(location)
        return currentWeatherDto.mapToEntity()
    }
}