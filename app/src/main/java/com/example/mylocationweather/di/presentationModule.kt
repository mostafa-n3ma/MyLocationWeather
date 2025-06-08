package com.example.mylocationweather.di

import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.mylocationweather.pressentation.viewModel.HomeViewMode
import org.koin.dsl.module

val PresentationModule = module {
    viewModel {
        HomeViewMode(
            getCurrentLocationUseCase = get(),
            getWeatherUseCase = get()
            )
    }
}