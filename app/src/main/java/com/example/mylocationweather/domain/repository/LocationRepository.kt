package com.example.mylocationweather.domain.repository

import com.example.mylocationweather.data.remote.model.LocationInfo

interface LocationRepository {
    suspend fun getCurrentLocation(): LocationInfo
}