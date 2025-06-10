package com.example.mylocationweather.pressentation.state

import com.example.mylocationweather.domain.model.LocationInfo
import com.example.mylocationweather.domain.model.WeatherEntity
import com.example.mylocationweather.pressentation.ui.components.AnimatedHeaderUiState
import com.example.mylocationweather.pressentation.ui.components.NextDaysItemUiState
import com.example.mylocationweather.pressentation.ui.components.TodayItemUiState
import com.example.mylocationweather.pressentation.utils.getCondition
import com.example.mylocationweather.ui.theme.DisplayMode


fun getHomeUiState(locationInfo: LocationInfo,weatherEntity: WeatherEntity): HomeUiState{
    return HomeUiState(
        displayMode = when(weatherEntity.isDay){
            "1"-> {
                DisplayMode.Day
            }
            "0"-> {
                DisplayMode.Night
            }
            else -> {
                DisplayMode.Day
            }
        },
        cityName = locationInfo.cityName,
        headerUiState = AnimatedHeaderUiState(
            weatherCondition = getCondition(weatherEntity.currentWeatherCode),
            temp = weatherEntity.currentTemperature.toString(),
            highTemp = weatherEntity.dailyTemperaturesMax[0].toString(),
            lowTemp = weatherEntity.dailyTemperaturesMin[0].toString()
        ),
        windSpeed = weatherEntity.currentWindSpeed.toString(),
        humidity = weatherEntity.currentHumidity.toString(),
        rain = weatherEntity.hourlyPrecipitationProbability.average().toString(),
        uvIndex = weatherEntity.currentUvIndex.toString(),
        pressure = weatherEntity.currentPressure.toString(),
        feelsLike = weatherEntity.feelsLikeTemperature.toString(),
        todayItems = getTodayItemUiStateList(
            conditionCodeList = weatherEntity.hourlyWeatherCodes,
            hours = weatherEntity.hours,
            temperatureList = weatherEntity.hourlyTemperatures.map { it.toString() }
        ),
        nextDaysItems = getNextDaysItemUiStateList(
            dayNames = weatherEntity.daysNames,
            conditionCodeList = weatherEntity.dailyWeatherCodes,
            highTempList = weatherEntity.dailyTemperaturesMax.map { it.toString() },
            lowTempList = weatherEntity.dailyTemperaturesMin.map { it.toString() }
        )
    )
}


fun getNextDaysItemUiStateList(
    dayNames: List<String>,
    conditionCodeList: List<Int>,
    highTempList: List<String>,
    lowTempList: List<String>
): List<NextDaysItemUiState>{
    return conditionCodeList.mapIndexed { index, code ->
        NextDaysItemUiState(
            dayName = dayNames[index],
            conditionCode = code,
            highTemp = highTempList[index],
            lowTemp = lowTempList[index]
        )
    }
}



fun getTodayItemUiStateList(
    conditionCodeList: List<Int>,
    hours: List<String>,
    temperatureList: List<String>
): List<TodayItemUiState> {

    return conditionCodeList.mapIndexed { index, code ->
        TodayItemUiState(
            conditionCode = code,
            temp = temperatureList[index],
            hour = hours[index]
        )
    }
}
