package com.example.mylocationweather.data.remote

import com.example.mylocationweather.data.remote.model.weatherModel.WeatherDto
import com.example.mylocationweather.data.remote.model.LocationInfo
import com.example.mylocationweather.domain.service.RemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherRemoteDataSource(
    private val client: HttpClient
): RemoteDataSource {

    override suspend fun getCurrentLocationWeatherInfo(location: LocationInfo): WeatherDto {
        return client.get(BASE_URL) {
            parameter("latitude", location.latitude)
            parameter("longitude", location.longitude)
            parameter("current_weather", true)
            parameter(
                "hourly",
                "temperature_2m,precipitation_probability,relativehumidity_2m,weathercode,uv_index,pressure_msl"
            )
            parameter(
                "daily",
                "temperature_2m_max,temperature_2m_min,weathercode,uv_index_max,precipitation_sum"
            )
            parameter("timezone", "auto")
        }.body()
    }


    companion object{
        private const val BASE_URL = "https://api.open-meteo.com/v1/forecast"
    }
}