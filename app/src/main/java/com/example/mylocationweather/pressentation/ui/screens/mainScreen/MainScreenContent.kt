package com.example.mylocationweather.pressentation.ui.screens.mainScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mylocationweather.R
import com.example.mylocationweather.pressentation.state.HomeUiState
import com.example.mylocationweather.pressentation.ui.components.AnimatedHeader
import com.example.mylocationweather.pressentation.ui.components.CitNameComponent
import com.example.mylocationweather.pressentation.ui.components.NextSevenDaysSection
import com.example.mylocationweather.pressentation.ui.components.TodaySection
import com.example.mylocationweather.pressentation.ui.components.WeatherDetailItem
import com.example.mylocationweather.pressentation.utils.GetScreenSizeDp
import com.example.mylocationweather.ui.theme.DisplayMode
import com.example.mylocationweather.ui.theme.MainBackgroundDayLinearGradient
import com.example.mylocationweather.ui.theme.MainBackgroundNightLinearGradient

fun calculateProgress(offset: Int, screenHeight: Int): Float {
    val expandThreshold = (screenHeight * 0.066f).toInt() // ~6.6% of height (~50px on 759 height)
    val collapseThreshold = (screenHeight * -0.066f).toInt() // -6.6% of height (~-50px)
    val range = expandThreshold - collapseThreshold

    return when {
        offset >= expandThreshold -> 1f
        offset <= collapseThreshold -> 0f
        else -> 1f - (expandThreshold - offset).toFloat() / range
    }
}


@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    homeUiState: HomeUiState,
    innerPadding: PaddingValues
) {
    val displayMode = homeUiState.displayMode

    val screenSize: Pair<Int, Int> = GetScreenSizeDp()


    val listState = rememberLazyListState()
    var progress by remember { mutableStateOf(1f) }



    LaunchedEffect(listState) {
        snapshotFlow {
            // Get the visible items info
            val visibleItems = listState.layoutInfo.visibleItemsInfo
            // Find our specific item by key
            visibleItems.firstOrNull { it.key == "ItemTracker" }?.offset
        }.collect { itemOffset ->
            itemOffset?.let { offset ->
                Log.d("ItemTracker", " offset: $offset : screenSize:$screenSize")
                progress = calculateProgress(offset, screenSize.second)
            } ?: run {
                // Item is not currently visible
                progress = if (listState.firstVisibleItemIndex > 0) 0f else 1f
            }
        }
    }




    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = when (displayMode) {
                    DisplayMode.Day -> MainBackgroundDayLinearGradient
                    DisplayMode.Night -> MainBackgroundNightLinearGradient
                }
            )
            .padding(top = innerPadding.calculateTopPadding(), start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(vertical = 24.dp)
        ) {
            item {
                CitNameComponent(
                    displayMode = displayMode,
                    cityName = homeUiState.city
                )
                Spacer(Modifier.height(12.dp))
            }

            item(key = "ItemTracker") {
                AnimatedHeader(
                    displayMode = displayMode,
                    progress = progress,
                    state = homeUiState.headerUiState,
                )
            }

            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        WeatherDetailItem(
                            modifier = Modifier.weight(1f),
                            displayMode = displayMode,
                            painter = when (displayMode) {
                                DisplayMode.Day -> painterResource(R.drawable.icon_fast_wind)
                                DisplayMode.Night -> painterResource(R.drawable.icon_fast_wind_night)
                            },
                            mainText = "${homeUiState.windSpeed} KM/h",
                            subText = "Wind"
                        )
                        WeatherDetailItem(
                            modifier = Modifier.weight(1f),
                            displayMode = displayMode,
                            painter = when (displayMode) {
                                DisplayMode.Day -> painterResource(R.drawable.icon_humidity)
                                DisplayMode.Night -> painterResource(R.drawable.icon_humidity_night)
                            },
                            mainText = "${homeUiState.humidity}${homeUiState.units.humidity}%",
                            subText = "Humidity"
                        )
                        WeatherDetailItem(
                            modifier = Modifier.weight(1f),
                            displayMode = displayMode,
                            painter = when (displayMode) {
                                DisplayMode.Day -> painterResource(R.drawable.icon_rain)
                                DisplayMode.Night -> painterResource(R.drawable.icon_rain_night)
                            },
                            mainText = "${homeUiState.rain}${homeUiState.units.perciption_u}",
                            subText = "Rain"
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        WeatherDetailItem(
                            modifier = Modifier.weight(1f),
                            displayMode = displayMode,
                            painter = when (displayMode) {
                                DisplayMode.Day -> painterResource(R.drawable.icon_uv)
                                DisplayMode.Night -> painterResource(R.drawable.icon_uv_night)
                            },
                            mainText = homeUiState.uvIndex,
                            subText = "Uv Index"
                        )
                        WeatherDetailItem(
                            modifier = Modifier.weight(1f),
                            displayMode = displayMode,
                            painter = when (displayMode) {
                                DisplayMode.Day -> painterResource(R.drawable.icon_arrow_down_05)
                                DisplayMode.Night -> painterResource(R.drawable.icon_arrow_down_05_night)
                            },
                            mainText = "${homeUiState.pressure} ${homeUiState.units.pressure_u}",
                            subText = "Pressure"
                        )
                        WeatherDetailItem(
                            modifier = Modifier.weight(1f),
                            displayMode = displayMode,
                            painter = when (displayMode) {
                                DisplayMode.Day -> painterResource(R.drawable.icon_temperature)
                                DisplayMode.Night -> painterResource(R.drawable.icon_temperature_night)
                            },
                            mainText = "${homeUiState.feelsLike}${homeUiState.units.temp_u}",
                            subText = "Feels like"
                        )
                    }
                }
            }
            item {
                TodaySection(
                    displayMode = displayMode,
                    todayStateList = homeUiState.todayItems,
                    tempUnit = homeUiState.units.temp_u
                )
            }

            item {
                NextSevenDaysSection(
                    displayMode = displayMode,
                    nextDaysItems = homeUiState.nextDaysItems,
                    tempUnit = homeUiState.units.temp_u
                )
            }


        }

    }
}