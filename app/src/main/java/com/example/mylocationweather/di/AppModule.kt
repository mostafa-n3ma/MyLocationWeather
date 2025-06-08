package com.example.mylocationweather.di

import com.example.mylocationweather.domain.usecase.GetCurrentLocationUseCase
import com.example.mylocationweather.domain.usecase.GetWeatherUseCase
import org.koin.dsl.module

val AppModule = module {
    single { GetCurrentLocationUseCase(locationRepository = get()) }
    single { GetWeatherUseCase(weatherRepository = get()) }
}