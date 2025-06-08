package com.example.mylocationweather

import android.app.Application
import com.example.mylocationweather.di.DataModule
import com.example.mylocationweather.di.AppModule
import com.example.mylocationweather.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApp)
            modules(
                AppModule,
                DataModule,
                PresentationModule
            )
        }
    }
}