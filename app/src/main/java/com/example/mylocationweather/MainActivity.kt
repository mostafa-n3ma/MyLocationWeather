package com.example.mylocationweather

import android.Manifest
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.mylocationweather.presentation.viewModel.HomeViewModel
import com.example.mylocationweather.pressentation.permisions.PermissionManager
import com.example.mylocationweather.pressentation.ui.HomeScreen
import com.example.mylocationweather.ui.theme.MyLocationWeatherTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {

    private lateinit var permissionManager: PermissionManager
    private lateinit var viewModel: HomeViewModel

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT))

        viewModel = getViewModel()


        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                viewModel.getCurrentLocation()
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }


        permissionManager = PermissionManager(
            context = this,
            permission = Manifest.permission.ACCESS_FINE_LOCATION,
            permissionLauncher = permissionLauncher,
            onPermissionGranted = {
                viewModel.getCurrentLocation()
            }
        )


        permissionManager.requestLocationPermission()

        setContent {
            MyLocationWeatherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding->
                    HomeScreen(
                        viewModel = viewModel,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}