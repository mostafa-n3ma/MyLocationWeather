package com.example.mylocationweather.pressentation.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mylocationweather.R
import com.example.mylocationweather.pressentation.utils.WeatherCondition
import com.example.mylocationweather.pressentation.utils.getConditionResourceId
import com.example.mylocationweather.ui.theme.ConditionDayTextColor
import com.example.mylocationweather.ui.theme.ConditionNightTextColor
import com.example.mylocationweather.ui.theme.DisplayMode
import com.example.mylocationweather.ui.theme.HighLowTempRangDaySpacerColor
import com.example.mylocationweather.ui.theme.HighLowTempRangDayTextColor
import com.example.mylocationweather.ui.theme.HighLowTempRangNightSpacerColor
import com.example.mylocationweather.ui.theme.HighLowTempRangNightTextColor
import com.example.mylocationweather.ui.theme.MainBackgroundDayLinearGradient
import com.example.mylocationweather.ui.theme.MainBackgroundNightLinearGradient
import com.example.mylocationweather.ui.theme.TempDayTextColor
import com.example.mylocationweather.ui.theme.TempNightTextColor
import com.example.mylocationweather.ui.theme.TempRangRowDayTextColor
import com.example.mylocationweather.ui.theme.TempRangRowNightTextColor
import com.example.mylocationweather.ui.theme.UrbanistFont
import kotlinx.coroutines.delay

data class AnimatedHeaderUiState(
    val weatherCondition: WeatherCondition,
    val temp: String,
    val highTemp: String,//dailyTemperaturesMax
    val lowTemp: String//dailyTemperaturesMin
)



@Composable
fun AnimatedHeader(
    modifier: Modifier = Modifier,
    displayMode: DisplayMode,
    isExpanded: Boolean ,
    state: AnimatedHeaderUiState
) {
    // Animated box height
    val boxHeight by animateDpAsState(
        targetValue = if (isExpanded) 355.dp else 143.dp,
        label = "BoxHeight"
    )

    // Animated image size
    val imageWidth by animateDpAsState(
        targetValue = if (isExpanded) 227.dp else 124.dp,
        label = "ImageWidth"
    )
    val imageHeight by animateDpAsState(
        targetValue = if (isExpanded) 200.dp else 112.dp,
        label = "ImageHeight"
    )

    // Derived alignments
    val imageAlignment = if (isExpanded) Alignment.TopCenter else Alignment.CenterStart
    val infoAlignment = if (isExpanded) Alignment.BottomCenter else Alignment.CenterEnd

    Box(
        modifier
            .fillMaxWidth()
            .height(boxHeight)
    ) {

        Box(
            modifier = Modifier
                .align(imageAlignment)
                .size(imageWidth, imageHeight),
            contentAlignment = Alignment.Center

        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(getConditionResourceId(state.weatherCondition,displayMode)),
                contentDescription = null
            )
        }


        TempInfoRang(
            modifier = Modifier.align(infoAlignment),
            displayMode = displayMode,
            temp = state.temp,
            condition = state.weatherCondition.conditionDescription,
            highTemp = state.highTemp,
            lowTemp = state.lowTemp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnimatedHeaderDay() {
    var isExpanded by remember { mutableStateOf(true) }

    // Simulate toggle after delay
    LaunchedEffect(Unit) {
        delay(2000) // Delay 1 second
        isExpanded = false
    }

    val state = AnimatedHeaderUiState(
        weatherCondition = WeatherCondition.CLEAR_SKY,
        temp = "35C",
        highTemp = "35C",
        lowTemp = "20C"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = MainBackgroundDayLinearGradient)
            .padding(horizontal = 16.dp)
    ) {
        AnimatedHeader(
            displayMode = DisplayMode.Day
            , isExpanded = isExpanded,
            state = state
        )

        Button(onClick = { isExpanded = !isExpanded }) {
            Text("Toggle")
        }
    }
}

@Preview(showBackground = true)
@Preview(name = "phone", device = "id:small_phone")
@Composable
fun PreviewAnimatedHeaderNight() {
    var isExpanded by remember { mutableStateOf(true) }

    // Simulate toggle after delay
    LaunchedEffect(Unit) {
        delay(2000) // Delay 1 second
        isExpanded = false
    }
    val state = AnimatedHeaderUiState(
        weatherCondition = WeatherCondition.CLEAR_SKY,
        temp = "35C",
        highTemp = "35C",
        lowTemp = "20C"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = MainBackgroundNightLinearGradient)
            .padding(horizontal = 16.dp)
    ) {
        AnimatedHeader(
            displayMode = DisplayMode.Night,
            isExpanded = isExpanded,
            state = state
            )

        Button(onClick = { isExpanded = !isExpanded }) {
            Text("Toggle")
        }
    }
}


@Composable
fun TempInfoRang(
    modifier: Modifier = Modifier,
    displayMode: DisplayMode,
    temp: String,
    condition: String,
    highTemp: String,
    lowTemp: String
) {
    Column(
        modifier = modifier.width(167.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        //temp
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "${temp.take(2)}°C",
                style = TextStyle(
                    fontFamily = UrbanistFont,
                    fontSize = 64.sp,
                    fontWeight = FontWeight(600),
                    lineHeight = 64.sp,
                    letterSpacing = 0.25.sp,
                    color = when (displayMode) {
                        DisplayMode.Day -> TempDayTextColor
                        DisplayMode.Night -> TempNightTextColor
                    }

                )
            )
            Text(
                text = condition,
                style = TextStyle(
                    fontFamily = UrbanistFont,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 16.sp,
                    letterSpacing = 0.25.sp,
                    color = when (displayMode) {
                        DisplayMode.Day -> ConditionDayTextColor
                        DisplayMode.Night -> ConditionNightTextColor
                    }

                )
            )
        }

        //rang
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = when (displayMode) {
                        DisplayMode.Day -> TempRangRowDayTextColor
                        DisplayMode.Night -> TempRangRowNightTextColor
                    },
                    shape = RoundedCornerShape(100.dp)
                )
                .padding(vertical = 8.dp, horizontal = 22.dp),
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
                    text = "${highTemp.take(2)}°C",
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
                    text = "${lowTemp.take(2)}°C",
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

@Preview
@Composable
fun TempInfoRangP(modifier: Modifier = Modifier) {
    Column {
        Box(
            Modifier.background(brush = MainBackgroundDayLinearGradient),
            contentAlignment = Alignment.Center
        ) {
            TempInfoRang(
                displayMode = DisplayMode.Day,
                temp = "25C",
                condition = "Partly Cloudy",
                highTemp = "35C",
                lowTemp = "20C",
            )

        }
        Box(
            Modifier.background(brush = MainBackgroundNightLinearGradient),
            contentAlignment = Alignment.Center
        ) {
            TempInfoRang(
                displayMode = DisplayMode.Night,
                temp = "25C",
                condition = "Partly Cloudy",
                highTemp = "35C",
                lowTemp = "20C",
            )
        }
    }

}