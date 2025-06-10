package com.example.mylocationweather.pressentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mylocationweather.R
import com.example.mylocationweather.ui.theme.CityTextDayColor
import com.example.mylocationweather.ui.theme.CityTextNightColor
import com.example.mylocationweather.ui.theme.DisplayMode
import com.example.mylocationweather.ui.theme.UrbanistFont

@Composable
fun CitNameComponent(
    modifier: Modifier = Modifier,
    displayMode: DisplayMode,
    cityName: String

) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ){
        Image(
            modifier = Modifier.size(24.dp),
            painter = when(displayMode){
                DisplayMode.Day -> painterResource(R.drawable.icon_location)
                DisplayMode.Night -> painterResource(R.drawable.icon_location_night)
            }

            ,
            contentDescription = null
        )

        Text(
            text = cityName,
            style = TextStyle(
                fontFamily = UrbanistFont,
                fontWeight = FontWeight(500),
                fontSize = 16.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
                color = when(displayMode){
                    DisplayMode.Day -> CityTextDayColor
                    DisplayMode.Night -> CityTextNightColor
                }
            )
        )

    }
    
}

@Preview
@Composable
fun CitNameComponentP(modifier: Modifier = Modifier) {
    Box(Modifier.background(color = Color.White)){
        CitNameComponent(displayMode = DisplayMode.Night, cityName = "Baghdad")
    }
}