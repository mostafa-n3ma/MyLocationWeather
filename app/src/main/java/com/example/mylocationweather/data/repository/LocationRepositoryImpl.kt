package com.example.mylocationweather.data.repository

import com.example.mylocationweather.domain.model.LocationInfo
import com.example.mylocationweather.domain.repository.LocationRepository
import com.example.mylocationweather.domain.service.LocationService

class LocationRepositoryImpl(
    private val locationService: LocationService
): LocationRepository {
    override suspend fun getCurrentLocation(): LocationInfo {
        return locationService.getCurrentLocation()
    }
}