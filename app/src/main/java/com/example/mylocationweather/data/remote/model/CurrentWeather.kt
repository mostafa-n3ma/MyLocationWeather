package com.example.mylocationweather.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CurrentWeather(
    val temperature: Double,           // °C
    @SerialName("windspeed") val windSpeed: Double,   // km/h
    @SerialName("weathercode") val weatherCode: Int,
    @SerialName("time") val time: String ,// ISO timestamp
    @SerialName("is_day") val isDay: Int
)