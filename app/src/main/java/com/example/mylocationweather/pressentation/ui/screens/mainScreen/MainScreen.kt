package com.example.mylocationweather.pressentation.ui.screens.mainScreen

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.mylocationweather.presentation.viewModel.HomeViewModel
import com.example.mylocationweather.pressentation.ui.screens.ErrorScreen
import com.example.mylocationweather.pressentation.ui.screens.LoadingScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    innerPadding: PaddingValues
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    when {
        isLoading -> {
            Log.d("loading test", " isLoading -> isLoading:$isLoading,homeUiState:$homeUiState")
            LoadingScreen(modifier)
        }
        homeUiState != null -> {
            Log.d("loading test", " homeUiState != null ->isLoading:$isLoading,homeUiState:$homeUiState")
            HomeScreenContent(
                innerPadding=innerPadding,
                modifier = modifier,
                homeUiState = homeUiState!!
            )
        }
        else -> {
            Log.d("loading test", " else-> isLoading:$isLoading,homeUiState:$homeUiState")
            ErrorScreen(
                modifier = modifier,
                onRetry = { viewModel.getCurrentWeather() }
            )
        }
    }
}





