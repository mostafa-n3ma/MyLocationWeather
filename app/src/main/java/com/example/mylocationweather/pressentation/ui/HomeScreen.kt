package com.example.mylocationweather.pressentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mylocationweather.presentation.viewModel.HomeViewModel
import com.example.mylocationweather.ui.theme.UrbanistFont

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    innerPadding: PaddingValues
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    when {
        isLoading -> LoadingScreen(modifier)
        homeUiState != null -> {
            HomeScreenContent(
                innerPadding=innerPadding,
                modifier = modifier,
                homeUiState = homeUiState!!
            )
        }
        else -> ErrorScreen(
            modifier = modifier,
            onRetry = { viewModel.getCurrentLocation() }
        )
    }
}

// Simplified Loading Screen
@Composable
private fun LoadingScreen(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

// Enhanced Error Screen
@Composable
private fun ErrorScreen(
    modifier: Modifier,
    onRetry: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Failed to load weather data",
                style = TextStyle(
                    fontFamily = UrbanistFont,
                    fontSize = 50.sp
                ),
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onRetry,
            ) {
                Text("Try Again")
            }
        }
    }
}





//
//fun checkAndRequestPermission(
//    permissionLauncher: ManagedActivityResultLauncher<String, Boolean>,
//    context: Context,
//    onPermissionGranted : ()-> Unit
//) {
//    val permissionCheckResult: Int = ContextCompat.checkSelfPermission(
//        context,
//        Manifest.permission.ACCESS_FINE_LOCATION
//    )
//    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
//        Log.d("Permissions Test", "permissionCheckResult:$permissionCheckResult ")
//        // If permission is granted,
//        onPermissionGranted()
//
//    } else {
//        Log.d("Permissions Test", "permissionCheckResult:$permissionCheckResult ")
//        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
//    }
//}