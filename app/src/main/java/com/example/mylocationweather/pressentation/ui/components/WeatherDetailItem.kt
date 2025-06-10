package com.example.mylocationweather.pressentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mylocationweather.R
import com.example.mylocationweather.ui.theme.DisplayMode
import com.example.mylocationweather.ui.theme.MainBackgroundDayLinearGradient
import com.example.mylocationweather.ui.theme.MainBackgroundNightLinearGradient
import com.example.mylocationweather.ui.theme.TempDayTextColor
import com.example.mylocationweather.ui.theme.TempNightTextColor
import com.example.mylocationweather.ui.theme.UrbanistFont
import com.example.mylocationweather.ui.theme.WeatherDetailItemDayBackground
import com.example.mylocationweather.ui.theme.WeatherDetailItemDayBorderColor
import com.example.mylocationweather.ui.theme.WeatherDetailItemDayMainTextColor
import com.example.mylocationweather.ui.theme.WeatherDetailItemDaySubTextColor
import com.example.mylocationweather.ui.theme.WeatherDetailItemNightBackground
import com.example.mylocationweather.ui.theme.WeatherDetailItemNightBorderColor
import com.example.mylocationweather.ui.theme.WeatherDetailItemNightMainTextColor
import com.example.mylocationweather.ui.theme.WeatherDetailItemNightSubTextColor

@Composable
fun WeatherDetailItem(
    modifier: Modifier = Modifier,
    displayMode: DisplayMode,
    painter: Painter,
    mainText : String,
    subText : String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(108.dp/115.dp)
            .background(
                color = when(displayMode){
                    DisplayMode.Day -> WeatherDetailItemDayBackground
                    DisplayMode.Night -> WeatherDetailItemNightBackground
                },
                shape = RoundedCornerShape(24.dp)
            )
            .border(
                width = 1.dp,
                color = when(displayMode){
                    DisplayMode.Day -> WeatherDetailItemDayBorderColor
                    DisplayMode.Night -> WeatherDetailItemNightBorderColor
                },
                shape = RoundedCornerShape(24.dp)
            )
        ,
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Image(
                painter = painter,
                contentDescription = null
            )
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = mainText,
                    style = TextStyle(
                        fontFamily = UrbanistFont,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(500),
                        lineHeight = 20.sp,
                        letterSpacing = 0.25.sp,
                        color = when (displayMode) {
                            DisplayMode.Day -> WeatherDetailItemDayMainTextColor
                            DisplayMode.Night -> WeatherDetailItemNightMainTextColor
                        }

                    )
                )
                Text(
                    text = subText,
                    style = TextStyle(
                        fontFamily = UrbanistFont,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        lineHeight = 20.sp,
                        letterSpacing = 0.25.sp,
                        color = when (displayMode) {
                            DisplayMode.Day -> WeatherDetailItemDaySubTextColor
                            DisplayMode.Night -> WeatherDetailItemNightSubTextColor
                        }

                    )
                )
            }

        }


    }
}

@Preview
@Composable
fun WeatherDetailItemDay(modifier: Modifier = Modifier) {
    Row(
        modifier.fillMaxWidth().background(brush = MainBackgroundDayLinearGradient),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ){
        WeatherDetailItem(
            modifier = Modifier.weight(1f),
            displayMode = DisplayMode.Day,
            painter = painterResource(R.drawable.icon_fast_wind),
            mainText = "13 KM/h",
            subText = "Wind"
        )
        WeatherDetailItem(
            modifier = Modifier.weight(1f),
            displayMode = DisplayMode.Day,
            painter = painterResource(R.drawable.icon_humidity),
            mainText = "24%",
            subText = "Humidity"
        )
        WeatherDetailItem(
            modifier = Modifier.weight(1f),
            displayMode = DisplayMode.Day,
            painter = painterResource(R.drawable.icon_rain),
            mainText = "2%",
            subText = "Rain"
        )

    }

}


@Preview
@Composable
fun WeatherDetailItemNight(modifier: Modifier = Modifier) {
    Row(
        modifier.fillMaxWidth().background(brush = MainBackgroundNightLinearGradient),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ){
        WeatherDetailItem(
            modifier = Modifier.weight(1f),
            displayMode = DisplayMode.Night,
            painter = painterResource(R.drawable.icon_fast_wind),
            mainText = "13 KM/h",
            subText = "Wind"
        )
        WeatherDetailItem(
            modifier = Modifier.weight(1f),
            displayMode = DisplayMode.Night,
            painter = painterResource(R.drawable.icon_humidity),
            mainText = "24%",
            subText = "Humidity"
        )
        WeatherDetailItem(
            modifier = Modifier.weight(1f),
            displayMode = DisplayMode.Night,
            painter = painterResource(R.drawable.icon_rain),
            mainText = "2%",
            subText = "Rain"
        )

    }

}