package com.example.mylocationweather.di

import com.example.mylocationweather.presentation.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val PresentationModule = module {
    viewModel {
        HomeViewModel(
            getCurrentLocationUseCase = get(),
            getWeatherUseCase = get()
            )
    }
}