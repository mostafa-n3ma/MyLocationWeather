package com.example.mylocationweather.domain.usecase

import android.location.Location
import com.example.mylocationweather.domain.model.LocationInfo
import com.example.mylocationweather.domain.repository.LocationRepository

class GetCurrentLocationUseCase(
    private val locationRepository: LocationRepository
) {
    fun getCurrentLocation(): LocationInfo {
        return locationRepository.getCurrentLocation()
    }
}