package com.example.mylocationweather.pressentation.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.mylocationweather.domain.model.LocationInfo
import com.example.mylocationweather.domain.model.WeatherEntity
import com.example.mylocationweather.pressentation.viewModel.HomeViewMode

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewMode: HomeViewMode
) {

    val currentWeather = viewMode.currentWeatherInfo.collectAsState()
    Log.d("from Home Screen", "HomeScreen: current weather inf : ${currentWeather.value}")

    HomeScreenContent(
        modifier = modifier,
        locationText = viewMode.currentLocation.collectAsState(),
        currentWeather = currentWeather
    )

}


@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    locationText: State<LocationInfo?>,
    currentWeather: State<WeatherEntity?>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = locationText.value?.let { "Lat: ${it.latitude}, Lon: ${it.longitude}: ${it.cityName}" } ?: "Location not available",
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp
            )
        )
        Text(
            text = currentWeather.value?.currentTemperature.toString(),
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp
            )
        )
    }
}

//@Preview
//@Composable
//fun HomeP(modifier: Modifier = Modifier) {
//    HomeScreenContent(modifier = modifier.background(color = Color.White) , locationText = "new Location")
//}


fun checkAndRequestPermission(
    permissionLauncher: ManagedActivityResultLauncher<String, Boolean>,
    context: Context,
    onPermissionGranted : ()-> Unit
) {
    val permissionCheckResult: Int = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
        Log.d("Permissions Test", "permissionCheckResult:$permissionCheckResult ")
        // If permission is granted,
        onPermissionGranted()

    } else {
        Log.d("Permissions Test", "permissionCheckResult:$permissionCheckResult ")
        permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }
}