package com.example.mylocationweather.data.location

import android.Manifest
import android.content.Context
import android.location.Location
import androidx.annotation.RequiresPermission
import com.example.mylocationweather.data.LocationFailureException
import com.example.mylocationweather.data.LocationNullException
import com.example.mylocationweather.domain.model.LocationInfo
import com.example.mylocationweather.domain.service.LocationService
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LocationServiceImpl(
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : LocationService {
    override suspend fun getCurrentLocation(): LocationInfo {
        TODO("Not yet implemented")
    }


}