package com.example.mylocationweather.domain.model


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
    val hourlyPressure: List<Double>
)