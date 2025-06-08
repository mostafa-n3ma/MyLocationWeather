package com.example.mylocationweather.pressentation.permisions

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat

class PermissionManager(
    private val context: Context,
    private val permission: String,
    private val permissionLauncher: ActivityResultLauncher<String>,
    private val onPermissionGranted: () -> Unit
) {
    fun requestLocationPermission() {
        val permissionStatus = ContextCompat.checkSelfPermission(context, permission)
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            onPermissionGranted()
        } else {
            permissionLauncher.launch(permission)
        }
    }
}