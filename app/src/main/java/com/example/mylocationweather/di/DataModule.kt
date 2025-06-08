package com.example.mylocationweather.di

import android.location.Geocoder
import com.example.mylocationweather.data.location.LocationServiceImpl
import com.example.mylocationweather.data.remote.WeatherRemoteDataSource
import com.example.mylocationweather.data.repository.LocationRepositoryImpl
import com.example.mylocationweather.data.repository.WeatherRepositoryImpl
import com.example.mylocationweather.domain.repository.LocationRepository
import com.example.mylocationweather.domain.repository.WeatherRepository
import com.example.mylocationweather.domain.service.LocationService
import com.example.mylocationweather.domain.service.RemoteDataSource
import com.google.android.gms.location.LocationServices
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.util.Locale

val DataModule = module {
    single { LocationServices.getFusedLocationProviderClient(androidContext()) }
    single {  Geocoder(androidContext(), Locale.getDefault()) }
    single<LocationService> { LocationServiceImpl(fusedLocationProviderClient = get(), geocoder = get()) }
    single < LocationRepository>{ LocationRepositoryImpl(locationService = get()) }


    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
            // Optional: add logging, timeout, etc.
        }
    }

    single<RemoteDataSource> { WeatherRemoteDataSource(client = get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(remoteDataSource = get()) }
}