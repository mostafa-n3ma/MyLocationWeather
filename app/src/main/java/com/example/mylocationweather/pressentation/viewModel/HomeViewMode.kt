package com.example.mylocationweather.pressentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylocationweather.domain.model.LocationInfo
import com.example.mylocationweather.domain.model.WeatherEntity
import com.example.mylocationweather.domain.usecase.GetCurrentLocationUseCase
import com.example.mylocationweather.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewMode(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val getWeatherUseCase: GetWeatherUseCase
): ViewModel() {
    val TAG = "HomeViewMode"


    private val _currentLocation: MutableStateFlow<LocationInfo?> = MutableStateFlow(null)
    val currentLocation = _currentLocation.asStateFlow()

    private val _currentWeatherInfo : MutableStateFlow<WeatherEntity?> = MutableStateFlow(null)
    val currentWeatherInfo = _currentWeatherInfo.asStateFlow()

     fun getCurrentLocation(){
         viewModelScope.launch {
             _currentLocation.update { getCurrentLocationUseCase.getCurrentLocation() }
             Log.d(TAG, "getCurrentLocation: ${currentLocation.value}")
             getCurrentWeather(_currentLocation.value!!)
             // TODO: handle null location
         }
    }

    fun getCurrentWeather(location: LocationInfo){
        viewModelScope.launch {
            _currentWeatherInfo.update { getWeatherUseCase.getWeatherInfo(location) }
        }
    }




}