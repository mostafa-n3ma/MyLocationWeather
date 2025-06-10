package com.example.mylocationweather.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mylocationweather.data.remote.model.WeatherDto
import com.example.mylocationweather.pressentation.utils.formatTime
import com.example.mylocationweather.pressentation.utils.getDayName


data class WeatherEntity(
    val currentTemperature: Double,
    val currentWindSpeed: Double,
    val currentWeatherCode: Int,
    val currentPressure: Double,  // from hourly.pressureMsl at currentIndex
    val currentTime: String,
    val currentHumidity: Int,
    val currentPrecipitationProbability: Int,
    val currentUvIndex: Double,
    val feelsLikeTemperature: Double? = null,
    val dailyTemperaturesMax: List<Double>,
    val dailyTemperaturesMin: List<Double>,
    val dailyWeatherCodes: List<Int>,
    val dailyUvIndexMax: List<Double>,
    val dailyPrecipitationSum: List<Double>,
    val hourlyTemperatures: List<Double>,
    val hourlyPrecipitationProbability: List<Int>,
    val hourlyHumidity: List<Int>,
    val hourlyWeatherCodes: List<Int>,
    val hourlyUvIndex: List<Double>,
    val hourlyPressure: List<Double>,
    val hours: List<String>,
    val daysNames: List<String>,
    val isDay: String
)



@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDto.mapToEntity(): WeatherEntity {
    val currentIndex = hourly.time.indexOf(currentWeather.time).takeIf { it != -1 } ?: 0

    return WeatherEntity(
        isDay = currentWeather.isDay.toString(),
        currentTemperature = currentWeather.temperature,//
        currentWindSpeed = currentWeather.windSpeed,
        currentWeatherCode = currentWeather.weatherCode,
        currentPressure = hourly.pressureMsl.getOrNull(currentIndex) ?: 0.0,  // pressure from hourly list
        currentTime = currentWeather.time,
        currentHumidity = hourly.relativeHumidity2m.getOrNull(currentIndex) ?: 0,
        currentPrecipitationProbability = hourly.precipitationProbability.getOrNull(currentIndex) ?: 0,
        currentUvIndex = hourly.uvIndex.getOrNull(currentIndex) ?: 0.0,
        feelsLikeTemperature = hourly.temperature2m.getOrNull(currentIndex),

        daysNames = daily.time.map { getDayName(it) },
        dailyTemperaturesMax = daily.temperatureMax,
        dailyTemperaturesMin = daily.temperatureMin,
        dailyWeatherCodes = daily.weatherCode,
        dailyUvIndexMax = daily.uvIndexMax,
        dailyPrecipitationSum = daily.precipitationSum,

        hours=hourly.time.take(24).map { formatTime(it) },
        hourlyTemperatures = hourly.temperature2m.take(24),
        hourlyPrecipitationProbability = hourly.precipitationProbability.take(24),
        hourlyHumidity = hourly.relativeHumidity2m.take(24),
        hourlyWeatherCodes = hourly.weatherCode.take(24),
        hourlyUvIndex = hourly.uvIndex.take(24),
        hourlyPressure = hourly.pressureMsl.take(24)
    )
}