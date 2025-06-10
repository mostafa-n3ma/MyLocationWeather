package com.example.mylocationweather.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Daily(
    @SerialName("time") val time: List<String>,
    @SerialName("temperature_2m_max") val temperatureMax: List<Double>,
    @SerialName("temperature_2m_min") val temperatureMin: List<Double>,
    @SerialName("weathercode") val weatherCode: List<Int>,
    @SerialName("uv_index_max") val uvIndexMax: List<Double>,
    @SerialName("precipitation_sum") val precipitationSum: List<Double>
)

//take first 24 item