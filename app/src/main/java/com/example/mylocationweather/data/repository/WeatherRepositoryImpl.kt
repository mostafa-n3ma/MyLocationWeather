package com.example.mylocationweather.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mylocationweather.data.remote.model.LocationInfo
import com.example.mylocationweather.domain.model.WeatherEntity
import com.example.mylocationweather.domain.model.mapToEntity
import com.example.mylocationweather.domain.repository.WeatherRepository
import com.example.mylocationweather.domain.service.RemoteDataSource

class WeatherRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): WeatherRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getCurrentLocationWeatherInfo(location: LocationInfo): WeatherEntity {
        val currentWeatherDto =  remoteDataSource.getCurrentLocationWeatherInfo(location)
        return currentWeatherDto.mapToEntity().copy(cityName = location.cityName)
    }
}