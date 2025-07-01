package com.example.mylocationweather.pressentation.ui.components

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mylocationweather.R
import com.example.mylocationweather.pressentation.utils.GetScreenSizeDp
import com.example.mylocationweather.pressentation.utils.WeatherCondition
import com.example.mylocationweather.pressentation.utils.dropShadow
import com.example.mylocationweather.pressentation.utils.getConditionResourceId
import com.example.mylocationweather.pressentation.utils.lerp
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
    val lowTemp: String, //dailyTemperaturesMinm
    val tempUnit: String
)


@Composable
fun AnimatedHeader(
    modifier: Modifier = Modifier,
    displayMode: DisplayMode,
    state: AnimatedHeaderUiState,
    progress: Float = 1f
) {
    val size: Pair<Int, Int> = GetScreenSizeDp()


    val boxHeight = lerp(143.dp, 355.dp, progress)
    val imageWidth = lerp(124.dp, 227.dp, progress)
    val imageHeight = lerp(112.dp, 200.dp, progress)
    val imageXOffset = lerp(0.dp, (size.first / 6).dp, progress)
    val imageYOffset = lerp(5.dp, 0.dp, progress)
    val infoXOffset = lerp((size.first / 2.1).dp, (size.first / 4).dp, progress)
    val infoYOffset = lerp(5.dp, 207.dp, progress)



    Box(
        modifier
            .fillMaxWidth()
            .height(boxHeight)
    ) {


        Box(
            modifier = Modifier
                .offset(x = imageXOffset, imageYOffset)

                .size(imageWidth, imageHeight),
            contentAlignment = Alignment.Center

        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .size(60.dp)
                    .dropShadow(
                        shape = CircleShape,
                        color = when (displayMode) {
                            DisplayMode.Day -> Color(0xFF050000)
                            DisplayMode.Night -> Color(0xFFC0B7FF)
                        },
                        blur = 100.dp,
                        spread = 1.dp
                    ),
            )

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(
                    getConditionResourceId(
                        state.weatherCondition,
                        displayMode
                    )
                ),
                contentDescription = null
            )
        }





        TempInfoRang(
            modifier = Modifier
                .offset(x = infoXOffset, y = infoYOffset),
            displayMode = displayMode,
            temp = state.temp,
            condition = state.weatherCondition.conditionDescription,
            highTemp = state.highTemp,
            lowTemp = state.lowTemp,
            tempUnit = state.tempUnit
        )
    }
}


@Preview(showBackground = true)
@Preview(name = "figma", device = "spec:width=428dp,height=926dp,dpi=420")
@Preview(name = "PHONE", device = Devices.PHONE)
@Preview(name = "PIXEL_4", device = Devices.PIXEL_4)
@Preview(name = "pixel 3", device = "spec:width=393dp,height=808dp,dpi=420")
@Preview(name = "small phone", device = "spec:width=360dp,height=640dp,dpi=420")
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
        temp = "35",
        highTemp = "35",
        lowTemp = "20",
        tempUnit = "C"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = MainBackgroundDayLinearGradient)
            .padding(horizontal = 16.dp)
    ) {
        AnimatedHeader(
            displayMode = DisplayMode.Day, progress = 1f,
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
        lowTemp = "20C",
        tempUnit = "C"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = MainBackgroundNightLinearGradient)
            .padding(horizontal = 16.dp)
    ) {
        AnimatedHeader(
            displayMode = DisplayMode.Night,
            progress = 1f,
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
    lowTemp: String,
    tempUnit: String
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
                text = "${temp}${tempUnit}",
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
                    painter = when (displayMode) {
                        DisplayMode.Day -> painterResource(R.drawable.icon_arrow_up)
                        DisplayMode.Night -> painterResource(R.drawable.icon_arrow_up_night)
                    },
                    contentDescription = null
                )
                Text(
                    text = "$highTemp$tempUnit",
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
                    painter = when (displayMode) {
                        DisplayMode.Day -> painterResource(R.drawable.icon_arrow_down)
                        DisplayMode.Night -> painterResource(R.drawable.icon_arrow_down_night)
                    },
                    contentDescription = null
                )
                Text(
                    text = "${lowTemp}$tempUnit",
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

//@Preview
//@Composable
//fun TempInfoRangP(modifier: Modifier = Modifier) {
//    Column {
//        Box(
//            Modifier.background(brush = MainBackgroundDayLinearGradient),
//            contentAlignment = Alignment.Center
//        ) {
//            TempInfoRang(
//                displayMode = DisplayMode.Day,
//                temp = "25",
//                condition = "Partly Cloudy",
//                highTemp = "35",
//                lowTemp = "20",
//                tempUnit = state.tempUnit,
//            )
//
//        }
//        Box(
//            Modifier.background(brush = MainBackgroundNightLinearGradient),
//            contentAlignment = Alignment.Center
//        ) {
//            TempInfoRang(
//                displayMode = DisplayMode.Night,
//                temp = "25",
//                condition = "Partly Cloudy",
//                highTemp = "35",
//                lowTemp = "20",
//                tempUnit = state.tempUnit,
//            )
//        }
//    }
//
//}


@Preview
@Composable
fun blurThing(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundDayLinearGradient)
            .padding(horizontal = 4.dp)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .blur(
                    600.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                )
                .background(Color.Black)
        )
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.clear_sky),
            contentDescription = null
        )
    }

}