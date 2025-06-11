package com.example.mylocationweather.pressentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
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
import com.example.mylocationweather.ui.theme.DailyItemDayText
import com.example.mylocationweather.ui.theme.DailyItemNightText
import com.example.mylocationweather.ui.theme.DailySectionLazyDayBackGround
import com.example.mylocationweather.ui.theme.DailySectionLazyNightBackGround
import com.example.mylocationweather.ui.theme.DisplayMode
import com.example.mylocationweather.ui.theme.HighLowTempRangDaySpacerColor
import com.example.mylocationweather.ui.theme.HighLowTempRangDayTextColor
import com.example.mylocationweather.ui.theme.HighLowTempRangNightSpacerColor
import com.example.mylocationweather.ui.theme.HighLowTempRangNightTextColor
import com.example.mylocationweather.ui.theme.MainBackgroundDayLinearGradient
import com.example.mylocationweather.ui.theme.MainBackgroundNightLinearGradient
import com.example.mylocationweather.ui.theme.Next7DyesBorderDayColor
import com.example.mylocationweather.ui.theme.Next7DyesBorderNightColor
import com.example.mylocationweather.ui.theme.Next7DyesTextDayColor
import com.example.mylocationweather.ui.theme.Next7DyesTextNightColor
import com.example.mylocationweather.ui.theme.UrbanistFont

data class NextDaysItemUiState(
    val dayName: String,
    val conditionCode: Int,
    val highTemp: String,
    val lowTemp: String,
)


@Composable
fun NextSevenDaysSection(
    modifier: Modifier = Modifier,
    displayMode: DisplayMode,
    nextDaysItems: List<NextDaysItemUiState>
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {

        Text(
            text = "Next 7 days",
            style = TextStyle(
                fontFamily = UrbanistFont,
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
                color = when (displayMode) {
                    DisplayMode.Day -> Next7DyesTextDayColor
                    DisplayMode.Night -> Next7DyesTextNightColor
                }
            )
        )


        Column(
            modifier = Modifier
                .background(
                    color = when (displayMode) {
                        DisplayMode.Day -> DailySectionLazyDayBackGround
                        DisplayMode.Night -> DailySectionLazyNightBackGround
                    },
                    shape = RoundedCornerShape(24.dp)
                )
                .border(
                    width = 1.dp,
                    color = when (displayMode) {
                        DisplayMode.Day -> Next7DyesBorderDayColor
                        DisplayMode.Night -> Next7DyesBorderNightColor
                    },
                    shape = RoundedCornerShape(24.dp)
                )
        ) {
            nextDaysItems.forEachIndexed { index, item ->
                val modifier = when (index) {
                    0 -> Modifier.border(
                        width = 1.dp,
                        color = when (displayMode) {
                            DisplayMode.Day -> Next7DyesBorderDayColor
                            DisplayMode.Night -> Next7DyesBorderNightColor
                        },
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    )

                    nextDaysItems.lastIndex -> Modifier.border(
                        width = 1.dp,
                        color = when (displayMode) {
                            DisplayMode.Day -> Next7DyesBorderDayColor
                            DisplayMode.Night -> Next7DyesBorderNightColor
                        },
                        shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                    )

                    else -> Modifier.border(
                        width = 1.dp,
                        color = when (displayMode) {
                            DisplayMode.Day -> Next7DyesBorderDayColor
                            DisplayMode.Night -> Next7DyesBorderNightColor
                        }
                    )
                }

                DailyItem(
                    modifier = modifier,
                    displayMode = displayMode,
                    dayName = item.dayName,
                    painter = painterResource(
                        getConditionResourceId(
                            condition = getCondition(item.conditionCode),
                            displayMode = displayMode
                        )
                    ),
                    highTemp = item.highTemp,
                    lowTemp = item.lowTemp,
                )
            }

        }


    }
}


@Preview
@Composable
fun NextSevenDaysSectionP(modifier: Modifier = Modifier) {

    val nextDaysItems = listOf(
        NextDaysItemUiState(
            dayName = "Monday",
            conditionCode = WeatherCondition.MAINLY_CLEAR.code,
            highTemp = "35",
            lowTemp = "25"
        ),
        NextDaysItemUiState(
            dayName = "Monday",
            conditionCode = WeatherCondition.MAINLY_CLEAR.code,
            highTemp = "35",
            lowTemp = "25"
        ),NextDaysItemUiState(
            dayName = "Monday",
            conditionCode = WeatherCondition.MAINLY_CLEAR.code,
            highTemp = "35",
            lowTemp = "25"
        ),
        NextDaysItemUiState(
            dayName = "Monday",
            conditionCode = WeatherCondition.MAINLY_CLEAR.code,
            highTemp = "35",
            lowTemp = "25"
        ),NextDaysItemUiState(
            dayName = "Monday",
            conditionCode = WeatherCondition.MAINLY_CLEAR.code,
            highTemp = "35",
            lowTemp = "25"
        ),NextDaysItemUiState(
            dayName = "Monday",
            conditionCode = WeatherCondition.MAINLY_CLEAR.code,
            highTemp = "35",
            lowTemp = "25"
        ),NextDaysItemUiState(
            dayName = "Monday",
            conditionCode = WeatherCondition.MAINLY_CLEAR.code,
            highTemp = "35",
            lowTemp = "25"
        ),

    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = MainBackgroundNightLinearGradient)
            .padding(horizontal = 16.dp)
    ) {
        NextSevenDaysSection(displayMode = DisplayMode.Night, nextDaysItems = nextDaysItems)
    }
}


@Composable
fun DailyItem(
    modifier: Modifier = Modifier,
    displayMode: DisplayMode,
    dayName: String,
    painter: Painter,
    highTemp: String,
    lowTemp: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(61.dp)
            .padding(horizontal = 16.dp),

        ) {
        Text(
            modifier = Modifier.align(Alignment.CenterStart),
            text = dayName,
            style = TextStyle(
                fontFamily = UrbanistFont,
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                lineHeight = 16.sp,
                letterSpacing = 0.25.sp,
                color = when (displayMode) {
                    DisplayMode.Day -> DailyItemDayText
                    DisplayMode.Night -> DailyItemNightText
                }
            )
        )

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(91.dp, 45.dp)
                .padding(vertical = 6.dp),
           contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(15.dp)
                    .dropShadow(
                        shape = CircleShape,
                        color = when(displayMode){
                            DisplayMode.Day -> Color(0xFF000000)
                            DisplayMode.Night -> Color(0xFF7A6FB0)
                        },
                        blur = 50.dp,
                        spread = 1.dp
                    )

                ,
            )

                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Inside
                )
            }


        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Image(
                    modifier = Modifier.size(12.dp),
                    painter = painterResource(R.drawable.icon_arrow_up),
                    contentDescription = null
                )
                Text(
                    text = "$highTemp°C",
                    style = TextStyle(
                        fontFamily = UrbanistFont,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        lineHeight = 16.sp,
                        letterSpacing = 0.25.sp,
                        color = when (displayMode) {
                            DisplayMode.Day -> HighLowTempRangDayTextColor
                            DisplayMode.Night -> HighLowTempRangNightTextColor
                        }

                    )
                )

            }
            Spacer(Modifier.width(8.dp))

            Spacer(
                Modifier
                    .width(1.dp)
                    .height(14.dp)
                    .background(
                        color = when (displayMode) {
                            DisplayMode.Day -> HighLowTempRangDaySpacerColor
                            DisplayMode.Night -> HighLowTempRangNightSpacerColor
                        }
                    )
            )
            Spacer(Modifier.width(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Image(
                    modifier = Modifier.size(12.dp),
                    painter = painterResource(R.drawable.icon_arrow_down),
                    contentDescription = null
                )
                Text(
                    text = "$lowTemp°C",
                    style = TextStyle(
                        fontFamily = UrbanistFont,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        lineHeight = 16.sp,
                        letterSpacing = 0.25.sp,
                        color = when (displayMode) {
                            DisplayMode.Day -> HighLowTempRangDayTextColor
                            DisplayMode.Night -> HighLowTempRangNightTextColor
                        }

                    )
                )

            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun DailyItemP(modifier: Modifier = Modifier) {
    val day = NextDaysItemUiState(
        dayName = "Monday",
        conditionCode = WeatherCondition.MAINLY_CLEAR.code,
        highTemp = "35",
        lowTemp = "25"
    )

    DailyItem(
        displayMode = DisplayMode.Day,
        dayName = day.dayName,
        painter = painterResource(
            getConditionResourceId(
                condition = getCondition(day.conditionCode),
                displayMode = DisplayMode.Day
            )
        ),
        highTemp = day.highTemp,
        lowTemp = day.lowTemp
    )


}


