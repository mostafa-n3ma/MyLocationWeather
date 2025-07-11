package com.example.mylocationweather.data.remote.model.weatherModel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Hourly(
    @SerialName("time") val time: List<String>,
    @SerialName("temperature_2m") val temperature2m: List<Double>,
    @SerialName("precipitation_probability") val precipitationProbability: List<Int>,  // %
    @SerialName("relativehumidity_2m") val relativeHumidity2m: List<Int>,             // %
    @SerialName("weathercode") val weatherCode: List<Int>,
    @SerialName("uv_index") val uvIndex: List<Double>,
    @SerialName("pressure_msl") val pressureMsl: List<Double>,
    @SerialName("is_day") val isDay: List<Int>
)


@Serializable
data class HourlyUnits(
    @SerialName("time") val time: String,
    @SerialName("temperature_2m") val temperature2m: String,//
    @SerialName("precipitation_probability") val precipitationProbability: String,//
    @SerialName("relativehumidity_2m") val relativeHumidity2m: String,//
    @SerialName("weathercode") val weatherCode: String,
    @SerialName("uv_index") val uvIndex: String,
    @SerialName("pressure_msl") val pressureMsl: String,//
    @SerialName("is_day") val isDay: String
)