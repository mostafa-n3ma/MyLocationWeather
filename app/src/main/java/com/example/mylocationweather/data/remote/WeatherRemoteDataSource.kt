package com.example.mylocationweather.data.remote

import com.example.mylocationweather.domain.model.LocationInfo
import com.example.mylocationweather.domain.model.WeatherInfo
import com.example.mylocationweather.domain.service.RemoteDataSource

class WeatherRemoteDataSource: RemoteDataSource {
    override fun getCurrentLocationWeatherInfo(location: LocationInfo): WeatherInfo {
        TODO("Not yet implemented")
    }
}