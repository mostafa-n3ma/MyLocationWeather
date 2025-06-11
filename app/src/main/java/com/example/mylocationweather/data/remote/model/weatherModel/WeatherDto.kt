package com.example.mylocationweather.data.remote.model.weatherModel

import com.example.mylocationweather.data.remote.model.weatherModel.Hourly
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("current_weather") val currentWeather: CurrentWeather,
    val hourly: Hourly,
    val daily: Daily
)