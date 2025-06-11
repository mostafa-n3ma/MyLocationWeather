package com.example.mylocationweather.pressentation.state

import com.example.mylocationweather.pressentation.ui.components.AnimatedHeaderUiState
import com.example.mylocationweather.pressentation.ui.components.NextDaysItemUiState
import com.example.mylocationweather.pressentation.ui.components.TodayItemUiState
import com.example.mylocationweather.ui.theme.DisplayMode

data class HomeUiState(
    val cityName: String,
    val headerUiState: AnimatedHeaderUiState,
    val units:unitsState,
    val windSpeed: String,
    val humidity: String,
    val rain: String,
    val uvIndex: String,
    val pressure: String,
    val feelsLike: String,
    val todayItems: List<TodayItemUiState>,
    val nextDaysItems: List<NextDaysItemUiState>,
    val displayMode: DisplayMode
)
