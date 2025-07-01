package com.example.mylocationweather.pressentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mylocationweather.R
import com.example.mylocationweather.pressentation.utils.WeatherCondition
import com.example.mylocationweather.pressentation.utils.dropShadow
import com.example.mylocationweather.pressentation.utils.getCondition
import com.example.mylocationweather.pressentation.utils.getConditionResourceId
import com.example.mylocationweather.ui.theme.DisplayMode
import com.example.mylocationweather.ui.theme.HourlyItemBackgroundDayColor
import com.example.mylocationweather.ui.theme.HourlyItemBackgroundNightColor
import com.example.mylocationweather.ui.theme.HourlyItemBorderDayColor
import com.example.mylocationweather.ui.theme.HourlyItemBorderNightColor
import com.example.mylocationweather.ui.theme.HourlyItemMainTextDayColor
import com.example.mylocationweather.ui.theme.HourlyItemMainTextNightColor
import com.example.mylocationweather.ui.theme.HourlyItemSubTextDayColor
import com.example.mylocationweather.ui.theme.HourlyItemSubTextNightColor
import com.example.mylocationweather.ui.theme.MainBackgroundDayLinearGradient
import com.example.mylocationweather.ui.theme.TodayTitleTextDayColor
import com.example.mylocationweather.ui.theme.TodayTitleTextNightColor
import com.example.mylocationweather.ui.theme.UrbanistFont

data class TodayItemUiState(
    val conditionCode: Int,
    val isDay: Int,
    val temp: String,
    val hour: String
)

@Composable
fun TodaySection(
    modifier: Modifier = Modifier,
    displayMode: DisplayMode,
    todayStateList: List<TodayItemUiState>,
    tempUnit: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Today",
            style = TextStyle(
                fontFamily = UrbanistFont,
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
                color = when (displayMode) {
                    DisplayMode.Day -> TodayTitleTextDayColor
                    DisplayMode.Night -> TodayTitleTextNightColor
                }
            )
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(todayStateList) { todayItemState ->
                val dailyDisplayMode = when (todayItemState.isDay) {
                    1 -> DisplayMode.Day
                    0 -> DisplayMode.Night
                    else -> DisplayMode.Day
                }
                HourlyItem(
                    displayMode = displayMode,
                    painter = painterResource(
                        getConditionResourceId(
                            condition = getCondition(
                                todayItemState.conditionCode
                            ), displayMode = dailyDisplayMode
                        )
                    ),
                    temp = todayItemState.temp,
                    hour = todayItemState.hour,
                    tempUnit = tempUnit
                )
            }
        }

    }
}


@Preview
@Composable
fun TodaySectionP(modifier: Modifier = Modifier) {
    val todayStateList: List<TodayItemUiState> = listOf(
        TodayItemUiState(
            temp = "25",
            hour = "11:00",
            conditionCode = WeatherCondition.MAINLY_CLEAR.code,
            isDay = 1
        ),
        TodayItemUiState(
            conditionCode = WeatherCondition.MAINLY_CLEAR.code,
            temp = "25",
            hour = "11:00",
            isDay = 1
        ),
        TodayItemUiState(
            conditionCode = WeatherCondition.MAINLY_CLEAR.code,
            temp = "25",
            hour = "11:00",
            isDay = 1
        ),
        TodayItemUiState(
            conditionCode = WeatherCondition.MAINLY_CLEAR.code,
            temp = "25",
            hour = "11:00",
            isDay = 0
        ),
        TodayItemUiState(
            conditionCode = WeatherCondition.MAINLY_CLEAR.code,
            temp = "25",
            hour = "11:00",
            isDay = 0
        ),
        TodayItemUiState(
            conditionCode = WeatherCondition.MAINLY_CLEAR.code,
            temp = "25",
            hour = "11:00",
            isDay = 0
        ),
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = MainBackgroundDayLinearGradient)
            .padding(horizontal = 16.dp)
    ) {
        TodaySection(
            displayMode = DisplayMode.Day,
            todayStateList = todayStateList,
            tempUnit = "c"
        )
    }
}


@Composable
fun HourlyItem(
    modifier: Modifier = Modifier,
    displayMode: DisplayMode,
    painter: Painter,
    temp: String,
    hour: String,
    tempUnit: String,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = modifier
                .align(Alignment.BottomCenter)
                .size(88.dp, 120.dp)
                .background(
                    color = when (displayMode) {
                        DisplayMode.Day -> HourlyItemBackgroundDayColor
                        DisplayMode.Night -> HourlyItemBackgroundNightColor
                    },
                    shape = RoundedCornerShape(20.dp)
                )
                .border(
                    width = 1.dp,
                    color = when (displayMode) {
                        DisplayMode.Day -> HourlyItemBorderDayColor
                        DisplayMode.Night -> HourlyItemBorderNightColor
                    },
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(bottom = 16.dp),
        ) {
            if (displayMode == DisplayMode.Day) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(20.dp)
                        .dropShadow(
                            shape = CircleShape,
                            color = Color(0xFF050000),
                            blur = 70.dp,
                            spread = 1.dp
                        ),
                )
            }

            Column(
                modifier = Modifier.align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "$temp$tempUnit",
                    style = TextStyle(
                        fontFamily = UrbanistFont,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        lineHeight = 16.sp,
                        letterSpacing = 0.25.sp,
                        color = when (displayMode) {
                            DisplayMode.Day -> HourlyItemMainTextDayColor
                            DisplayMode.Night -> HourlyItemMainTextNightColor
                        }
                    )
                )

                Text(
                    text = hour,
                    style = TextStyle(
                        fontFamily = UrbanistFont,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        lineHeight = 16.sp,
                        letterSpacing = 0.25.sp,
                        color = when (displayMode) {
                            DisplayMode.Day -> HourlyItemSubTextDayColor
                            DisplayMode.Night -> HourlyItemSubTextNightColor
                        }
                    )
                )
            }

        }


        Image(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(64.dp, 58.dp)
                .offset(y = -15.dp),
            painter = painter,
            contentDescription = null
        )
    }

}

//@Preview
//@Composable
//fun HourlyPreview(modifier: Modifier = Modifier) {
//    Column(
//        modifier
//            .fillMaxSize()
//            .background(MainBackgroundDayLinearGradient)
//    ) {
//        Row(
//            modifier
//                .fillMaxWidth()
//                .padding(start = 16.dp),
//            verticalAlignment = Alignment.Bottom,
//            horizontalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            HourlyItem(
//                displayMode = DisplayMode.Day,
//                painter = painterResource(R.drawable.clear_sky),
//                temp = "25",
//                hour = "11:00",
//            )
//            HourlyItem(
//                displayMode = DisplayMode.Day,
//                painter = painterResource(R.drawable.clear_sky),
//                temp = "25",
//                hour = "11:00",
//            )
//            HourlyItem(
//                displayMode = DisplayMode.Day,
//                painter = painterResource(R.drawable.clear_sky),
//                temp = "25",
//                hour = "11:00",
//            )
//
//        }
//    }
//
//}