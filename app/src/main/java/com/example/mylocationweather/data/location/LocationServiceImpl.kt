package com.example.mylocationweather.data.location

import android.Manifest
import android.location.Address
import android.location.Geocoder
import androidx.annotation.RequiresPermission
import com.example.mylocationweather.data.LocationFailureException
import com.example.mylocationweather.data.LocationNullException
import com.example.mylocationweather.data.remote.model.LocationInfo
import com.example.mylocationweather.domain.service.LocationService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import kotlinx.coroutines.tasks.await

class LocationServiceImpl(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val geocoder: Geocoder
) : LocationService {

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    override suspend fun getCurrentLocation(): LocationInfo {
        return try {
            val currentLocation = fusedLocationProviderClient
                .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .await()
            currentLocation?.let {
                val cityName = getCityNameFromLatLng(it.latitude, it.longitude)
                LocationInfo(latitude = it.latitude, longitude = it.longitude, cityName = cityName)
            } ?: throw LocationNullException()
        } catch (e: Exception) {
            throw LocationFailureException(e.message.orEmpty())
        }
    }

    fun getCityNameFromLatLng(latitude: Double, longitude: Double): String {
        return try {
            val addresses: List<Address>? = geocoder.getFromLocation(latitude, longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                addresses[0].locality ?: addresses[0].subAdminArea ?: addresses[0].adminArea
                ?: throw LocationNullException()
            } else {
                throw LocationNullException()
            }
        } catch (e: Exception) {
            throw LocationFailureException(e.message ?: "Failed to get city name")
        }
    }

}