package com.example.mylocationweather.pressentation.utils

import androidx.compose.ui.graphics.painter.Painter
import com.example.mylocationweather.R
import com.example.mylocationweather.ui.theme.DisplayMode

enum class WeatherCondition(
    val conditionDescription: String,
    val code: Int,
    val imageDayResourceId: Int,
    val imageNightResourceId: Int
) {
    CLEAR_SKY(conditionDescription = "Clear sky", code = 0, imageDayResourceId = R.drawable.clear_sky,imageNightResourceId = R.drawable.clear_sky_night),
    MAINLY_CLEAR(conditionDescription = "Mainly clear", code = 1, imageDayResourceId = R.drawable.mainly_clear,imageNightResourceId = R.drawable.mainly_clear_night),
    PARTLY_CLOUDY(conditionDescription = "Partly cloudy", code = 2, imageDayResourceId = R.drawable.partly_cloudy,imageNightResourceId = R.drawable.partly_cloudy_night),
    OVERCAST(conditionDescription = "Overcast", code = 3, imageDayResourceId = R.drawable.overcast,imageNightResourceId = R.drawable.overcase_night),
    FOG(conditionDescription = "Foggy", code = 45, imageDayResourceId = R.drawable.fog,imageNightResourceId = R.drawable.fog_night),
    RIME_FOG(conditionDescription = "Rime Fog", code = 48, imageDayResourceId = R.drawable.rime_foge,imageNightResourceId = R.drawable.rime_foge_night),
    DRIZZLE_LIGHT(conditionDescription = "Drizzle Light", code = 51, imageDayResourceId = R.drawable.drizzle_light,imageNightResourceId = R.drawable.drizzle_light_night),
    DRIZZLE_MODERATE(conditionDescription = "Drizzle Moderate", code = 53, imageDayResourceId = R.drawable.drizzle_moderate,imageNightResourceId = R.drawable.drizzle_moderate_night),
    DRIZZLE_INTENSITY(conditionDescription = "Drizzle Intensity", code = 55, imageDayResourceId = R.drawable.drizzle_intensity,imageNightResourceId = R.drawable.drizzle_intensity_night),
    LIGHT_FREEZING_DRIZZLE(conditionDescription = "Light freezing drizzle", code = 56, imageDayResourceId = R.drawable.freezing_drizzle_light,imageNightResourceId = R.drawable.freezing_drizzle_light_night),
    INTENSITY_FREEZING_DRIZZLE(conditionDescription = "Intensity freezing drizzle", code = 57, imageDayResourceId = R.drawable.freezing_drizzle_intensity,imageNightResourceId = R.drawable.freezing_drizzle_intensity_night),
    SLIGHT_RAIN(conditionDescription = "Slight Rain", code = 61, imageDayResourceId = R.drawable.rain_slight,imageNightResourceId = R.drawable.rain_slight_night),
    MODERATE_RAIN(conditionDescription = "Moderate rain", code = 63, imageDayResourceId = R.drawable.rain_moderate,imageNightResourceId = R.drawable.rain_moderate_night),
    HEAVY_INTENSITY_RAIN(conditionDescription = "Heavy rain", code = 65, imageDayResourceId = R.drawable.rain_intensity,imageNightResourceId = R.drawable.rain_intensity_night),
    LIGHT_FREEZING_RAIN(conditionDescription = "Light freezing rain", code = 66, imageDayResourceId = R.drawable.frezzing_rain_light,imageNightResourceId = R.drawable.frezzing_rain_light_night),
    HEAVY_INTENSITY_FREEZING_RAIN(conditionDescription = "Heavy freezing rain", code = 67, imageDayResourceId = R.drawable.frezzing_rain_intensity,imageNightResourceId = R.drawable.frezzing_rain_intensity_night),
    SLIGHT_SNOW_FALL(conditionDescription = "Light snow", code = 71, imageDayResourceId = R.drawable.snow_fall_light,imageNightResourceId = R.drawable.snow_fall_light_night),
    MODERATE_SNOW_FALL(conditionDescription = "Moderate snow", code = 73, imageDayResourceId = R.drawable.snow_fall_moderate,imageNightResourceId = R.drawable.snow_fall_moderate_night),
    HEAVY_INTENSITY_SNOW_FALL(conditionDescription = "Heavy snow", code = 75, imageDayResourceId = R.drawable.snow_fall_heavy,imageNightResourceId = R.drawable.snow_fall_heavy_night),
    SNOW_GRAINS(conditionDescription = "Snow grains", code = 77, imageDayResourceId = R.drawable.snow_grains,imageNightResourceId = R.drawable.snow_grains_night),
    SLIGHT_RAIN_SHOWERS(conditionDescription = "Light rain showers", code = 80, imageDayResourceId = R.drawable.rain_shower_light,imageNightResourceId = R.drawable.rain_shower_light_night),
    MODERATE_RAIN_SHOWERS(conditionDescription = "Moderate rain showers", code = 81, imageDayResourceId = R.drawable.rain_shower_moderate,imageNightResourceId = R.drawable.rain_shower_moderate_night),
    VIOLENT_RAIN_SHOWERS(conditionDescription = "Violent rain showers", code = 82, imageDayResourceId = R.drawable.rain_shower_violent,imageNightResourceId = R.drawable.rain_shower_violent_night),
    SLIGHT_SNOW_SHOWERS(conditionDescription = "Light snow showers", code = 85, imageDayResourceId = R.drawable.snow_shower_slight,imageNightResourceId = R.drawable.snow_shower_slight_night),
    HEAVY_SNOW_SHOWERS(conditionDescription = "Heavy snow showers", code = 86, imageDayResourceId = R.drawable.snow_shower_heavy,imageNightResourceId = R.drawable.snow_shower_heavy_night),
    SLIGHT_THUNDERSTORM(conditionDescription = "Slight thunderstorm", code = 95, imageDayResourceId = R.drawable.slight_thunderstorm,imageNightResourceId = R.drawable.slight_thunderstorm_night),
    SLIGHT_THUNDERSTORM_WITH_HAIL(conditionDescription = "Thunderstorm with slight hail", code = 96, imageDayResourceId = R.drawable.thunderstorm_with_slight_hail,imageNightResourceId = R.drawable.thunderstorm_with_slight_hail_night),
    HEAVY_THUNDERSTORM_WITH_HAIL(conditionDescription = "Thunderstorm with heavy hail", code = 99, imageDayResourceId = R.drawable.thunderstrom_with_heavy_hail,imageNightResourceId = R.drawable.thunderstrom_with_heavy_hail_night),
}

fun getCondition(code: Int): WeatherCondition {
    return WeatherCondition.entries.firstOrNull { it.code == code }
        ?: WeatherCondition.CLEAR_SKY
}

fun getConditionResourceId(condition: WeatherCondition, displayMode: DisplayMode): Int{
    return when(displayMode){
        DisplayMode.Day -> condition.imageDayResourceId
        DisplayMode.Night -> condition.imageNightResourceId
    }
}