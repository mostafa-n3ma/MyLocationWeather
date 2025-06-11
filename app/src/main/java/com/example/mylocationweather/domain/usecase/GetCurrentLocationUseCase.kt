package com.example.mylocationweather.domain.usecase

import com.example.mylocationweather.data.remote.model.LocationInfo
import com.example.mylocationweather.domain.repository.LocationRepository

class GetCurrentLocationUseCase(
    private val locationRepository: LocationRepository
) {
    suspend fun getCurrentLocation(): LocationInfo {
        return locationRepository.getCurrentLocation()
    }
}