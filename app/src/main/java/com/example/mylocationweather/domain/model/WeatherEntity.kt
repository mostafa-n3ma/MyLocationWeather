package com.example.mylocationweather.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mylocationweather.data.remote.model.weatherModel.WeatherDto
import com.example.mylocationweather.pressentation.utils.formatTime
import com.example.mylocationweather.pressentation.utils.getDayName


data class WeatherEntity(
    val cityName: String = "",
    val currentTemperature: Int,
    val currentWindSpeed: Int,
    val currentWeatherCode: Int,
    val currentPressure: Int,  // from hourly.pressureMsl at currentIndex
    val currentTime: String,
    val currentHumidity: Int,
    val currentPrecipitationProbability: Int,
    val currentUvIndex: Int,
    val feelsLikeTemperature: Int? = null,
    val dailyTemperaturesMax: List<Int>,
    val dailyTemperaturesMin: List<Int>,
    val dailyWeatherCodes: List<Int>,
    val dailyUvIndexMax: List<Int>,
    val dailyPrecipitationSum: List<Int>,
    val hourlyTemperatures: List<Int>,
    val hourlyPrecipitationProbability: List<Int>,
    val hourlyHumidity: List<Int>,
    val hourlyWeatherCodes: List<Int>,
    val hourlyUvIndex: List<Int>,
    val hourlyPressure: List<Int>,
    val hours: List<String>,
    val daysNames: List<String>,
    val isDay: String,
    val hourlyIsDay: List<Int>,
    val temperatureUnit: String,
    val pressureUnit: String,
    val humidityUnit: String,
    val precipitationProbabilityUnit: String
)



@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDto.mapToEntity(): WeatherEntity {
    val currentIndex = hourly.time.indexOf(currentWeather.time).takeIf { it != -1 } ?: 0

    return WeatherEntity(
        currentTemperature = currentWeather.temperature.toInt(),
        currentWindSpeed = currentWeather.windSpeed.toInt(),
        currentWeatherCode = currentWeather.weatherCode,

        currentPressure = hourly.pressureMsl.getOrNull(currentIndex)?.toInt() ?: 0,//
        currentTime = currentWeather.time,
        currentHumidity = hourly.relativeHumidity2m.getOrNull(currentIndex) ?: 0,
        currentPrecipitationProbability = hourly.precipitationProbability.getOrNull(currentIndex) ?: 0,
        currentUvIndex = hourly.uvIndex.getOrNull(currentIndex)?.toInt() ?: 0,  // pressure from hourly list
        feelsLikeTemperature = hourly.temperature2m.getOrNull(currentIndex)?.toInt()?: 0,
        dailyTemperaturesMax = daily.temperatureMax.map { it.toInt() },

        dailyTemperaturesMin = daily.temperatureMin.map { it.toInt() },
        dailyWeatherCodes = daily.weatherCode,

        dailyUvIndexMax = daily.uvIndexMax.map { it.toInt() },
        dailyPrecipitationSum = daily.precipitationSum.map { it.toInt() },
        hourlyTemperatures = hourly.temperature2m.take(24).map { it.toInt() },

        hourlyPrecipitationProbability = hourly.precipitationProbability.take(24),
        hourlyHumidity = hourly.relativeHumidity2m.take(24),
        hourlyWeatherCodes = hourly.weatherCode.take(24),
        hourlyUvIndex = hourly.uvIndex.take(24).map { it.toInt() },
        hourlyPressure = hourly.pressureMsl.take(24).map { it.toInt() },
        hours=hourly.time.take(24).map { formatTime(it) },

        daysNames = daily.time.map { getDayName(it) },
        isDay = currentWeather.isDay.toString(),
        hourlyIsDay = hourly.isDay,
        temperatureUnit = hourlyUnits.temperature2m,
        pressureUnit = hourlyUnits.pressureMsl,
        humidityUnit = hourlyUnits.relativeHumidity2m,
        precipitationProbabilityUnit = hourlyUnits.precipitationProbability,
    )
}