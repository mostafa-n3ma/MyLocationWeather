package com.example.mylocationweather.pressentation.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.mylocationweather.ui.theme.DisplayMode
import com.example.mylocationweather.ui.theme.MainBackgroundDayLinearGradient
import com.example.mylocationweather.ui.theme.MainBackgroundNightLinearGradient

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    homeUiState: HomeUiState,
    innerPadding: PaddingValues
) {
    val displayMode = homeUiState.displayMode



    val listState = rememberLazyListState()
    var isHeaderExpanded by remember { mutableStateOf(true) }
    var previousOffset by remember { mutableStateOf(0) }




    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect { currentOffset ->
                Log.d("LazyShit", "HomeScreenContent: $currentOffset")
//                isHeaderExpanded = when {
//                    currentOffset == 0 -> true
//                    currentOffset < previousOffset -> true   // Scroll down → expand
//                    currentOffset > previousOffset -> false  // Scroll up → collapse
//                    else -> isHeaderExpanded
//                }
//                previousOffset = currentOffset
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
            .padding(top = innerPadding.calculateTopPadding(), start = 16.dp, end = 16.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(24.dp))
        CitNameComponent(
            modifier = Modifier.clickable{
                isHeaderExpanded = !isHeaderExpanded
            },
            displayMode = displayMode,
            cityName = homeUiState.cityName
        )
        Spacer(Modifier.height(12.dp))


        LazyColumn(
            state = listState,
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(vertical = 24.dp)
        ){

            item {
                // TODO: AnimatedHeaderUiState to state
                AnimatedHeader(
                    displayMode = displayMode,
                    isExpanded = isHeaderExpanded,
                    state = homeUiState.headerUiState,
                )
            }

            item {
                Column (
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ){
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ){
                        WeatherDetailItem(
                            modifier = Modifier.weight(1f),
                            displayMode = displayMode,
                            painter = when(displayMode){
                                DisplayMode.Day -> painterResource(R.drawable.icon_fast_wind)
                                DisplayMode.Night -> painterResource(R.drawable.icon_fast_wind_night)
                            },
                            mainText = "${homeUiState.windSpeed} KM/h",
                            subText = "Wind"
                        )
                        WeatherDetailItem(
                            modifier = Modifier.weight(1f),
                            displayMode = displayMode,
                            painter = when(displayMode){
                                DisplayMode.Day -> painterResource(R.drawable.icon_humidity)
                                DisplayMode.Night -> painterResource(R.drawable.icon_humidity_night)
                            },
                            mainText = "${homeUiState.humidity}%",
                            subText = "Humidity"
                        )
                        WeatherDetailItem(
                            modifier = Modifier.weight(1f),
                            displayMode = displayMode,
                            painter = when(displayMode){
                                DisplayMode.Day -> painterResource(R.drawable.icon_rain)
                                DisplayMode.Night -> painterResource(R.drawable.icon_rain_night)
                            },
                            mainText = "${homeUiState.rain.take(2)}%",
                            subText = "Rain"
                        )
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ){
                        WeatherDetailItem(
                            modifier = Modifier.weight(1f),
                            displayMode = displayMode,
                            painter = when(displayMode){
                                DisplayMode.Day -> painterResource(R.drawable.icon_uv)
                                DisplayMode.Night -> painterResource(R.drawable.icon_uv_night)
                            },
                            mainText = homeUiState.uvIndex,
                            subText = "Uv Index"
                        )
                        WeatherDetailItem(
                            modifier = Modifier.weight(1f),
                            displayMode = displayMode,
                            painter = when(displayMode){
                                DisplayMode.Day -> painterResource(R.drawable.icon_arrow_down_05)
                                DisplayMode.Night -> painterResource(R.drawable.icon_arrow_down_05_night)
                            },
                            mainText = "${homeUiState.pressure.take(3)} hPa",
                            subText = "Pressure"
                        )
                        WeatherDetailItem(
                            modifier = Modifier.weight(1f),
                            displayMode = displayMode,
                            painter = when(displayMode){
                                DisplayMode.Day -> painterResource(R.drawable.icon_temperature)
                                DisplayMode.Night -> painterResource(R.drawable.icon_temperature_night)
                            },
                            mainText = "${homeUiState.feelsLike.take(2)}°C",
                            subText = "Feels like"
                        )
                    }
                }
            }
            item {
                TodaySection(
                    displayMode = displayMode,
                    todayStateList = homeUiState.todayItems
                )
            }

            item {
                NextSevenDaysSection(
                    displayMode = displayMode,
                    nextDaysItems = homeUiState.nextDaysItems
                )
            }


        }







    }
}