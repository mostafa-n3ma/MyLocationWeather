package com.example.mylocationweather.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylocationweather.domain.model.LocationInfo
import com.example.mylocationweather.domain.model.WeatherEntity
import com.example.mylocationweather.domain.usecase.GetCurrentLocationUseCase
import com.example.mylocationweather.domain.usecase.GetWeatherUseCase
import com.example.mylocationweather.pressentation.state.HomeUiState
import com.example.mylocationweather.pressentation.state.getHomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    val TAG = "HomeViewModel"

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _currentLocation: MutableStateFlow<LocationInfo?> = MutableStateFlow(null)
    val currentLocation = _currentLocation.asStateFlow()

    private val _currentWeatherInfo: MutableStateFlow<WeatherEntity?> = MutableStateFlow(null)
    val currentWeatherInfo = _currentWeatherInfo.asStateFlow()

    private val _homeUiState: MutableStateFlow<HomeUiState?> = MutableStateFlow(null)
    val homeUiState = _homeUiState.asStateFlow()

    fun getCurrentLocation() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val location = getCurrentLocationUseCase.getCurrentLocation()
                _currentLocation.value = location
                Log.d(TAG, "getCurrentLocation: ${location}")

                    getCurrentWeather(location)

            } catch (e: Exception) {
                Log.e(TAG, "Error getting location: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun getCurrentWeather(location: LocationInfo) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _currentWeatherInfo.update {  getWeatherUseCase.getWeatherInfo(location)}

                    updateUiState(location, _currentWeatherInfo.value!!)
                Log.d(TAG, "getCurrentWeather: isDay:${_currentWeatherInfo.value!!.isDay}")

            } catch (e: Exception) {
                Log.e(TAG, "Error getting weather: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun updateUiState(locationInfo: LocationInfo, weatherEntity: WeatherEntity) {
        viewModelScope.launch {
            try {
                _homeUiState.update {
                    getHomeUiState(
                        locationInfo = locationInfo,
                        weatherEntity = weatherEntity
                    )
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error updating UI state: ${e.message}")
            }
        }
    }
}