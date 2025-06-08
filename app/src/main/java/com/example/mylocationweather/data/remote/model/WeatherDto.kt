package com.example.mylocationweather.data.remote.model
import com.example.mylocationweather.domain.model.WeatherEntity
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName



@Serializable
data class WeatherDto(
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("current_weather") val currentWeather: CurrentWeather,
    val hourly: Hourly,
    val daily: Daily
)


fun WeatherDto.mapToEntity(): WeatherEntity {
    val currentIndex = hourly.time.indexOf(currentWeather.time).takeIf { it != -1 } ?: 0

    return WeatherEntity(
        currentTemperature = currentWeather.temperature,
        currentWindSpeed = currentWeather.windSpeed,
        currentWeatherCode = currentWeather.weatherCode,
        currentPressure = hourly.pressureMsl.getOrNull(currentIndex) ?: 0.0,  // pressure from hourly list
        currentTime = currentWeather.time,
        currentHumidity = hourly.relativeHumidity2m.getOrNull(currentIndex) ?: 0,
        currentPrecipitationProbability = hourly.precipitationProbability.getOrNull(currentIndex) ?: 0,
        currentUvIndex = hourly.uvIndex.getOrNull(currentIndex) ?: 0.0,
        feelsLikeTemperature = hourly.temperature2m.getOrNull(currentIndex),
        dailyTemperaturesMax = daily.temperatureMax,
        dailyTemperaturesMin = daily.temperatureMin,
        dailyWeatherCodes = daily.weatherCode,
        dailyUvIndexMax = daily.uvIndexMax,
        dailyPrecipitationSum = daily.precipitationSum,
        hourlyTemperatures = hourly.temperature2m,
        hourlyPrecipitationProbability = hourly.precipitationProbability,
        hourlyHumidity = hourly.relativeHumidity2m,
        hourlyWeatherCodes = hourly.weatherCode,
        hourlyUvIndex = hourly.uvIndex,
        hourlyPressure = hourly.pressureMsl
    )
}





