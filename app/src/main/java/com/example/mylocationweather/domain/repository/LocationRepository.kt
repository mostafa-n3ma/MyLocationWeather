package com.example.mylocationweather.domain.repository

import com.example.mylocationweather.domain.model.LocationInfo

interface LocationRepository {
    suspend fun getCurrentLocation(): LocationInfo
}