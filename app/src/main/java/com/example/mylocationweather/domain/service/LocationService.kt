package com.example.mylocationweather.domain.service


import com.example.mylocationweather.domain.model.LocationInfo

interface LocationService {
    suspend fun getCurrentLocation(): LocationInfo
}


