package com.example.mylocationweather.domain.service


import com.example.mylocationweather.data.remote.model.LocationInfo

interface LocationService {
    suspend fun getCurrentLocation(): LocationInfo
}


