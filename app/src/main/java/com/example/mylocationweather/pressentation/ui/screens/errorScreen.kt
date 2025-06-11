package com.example.mylocationweather.pressentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mylocationweather.R
import com.example.mylocationweather.ui.theme.MainBackgroundDayLinearGradient
import com.example.mylocationweather.ui.theme.MainBackgroundNightLinearGradient
import com.example.mylocationweather.ui.theme.UrbanistFont

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    onRetry: () -> Unit
) {
    Column (
        modifier = modifier.fillMaxSize().background(MainBackgroundNightLinearGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            modifier = Modifier,
            painter = painterResource(R.drawable.partly_cloudy),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(20.dp),
            text = "someThing Went Wrong please wait until we fix it....",
            style = TextStyle(
                fontFamily = UrbanistFont,
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )
        Box(
            contentAlignment = Alignment.Center
        ){
            Text(
                modifier = Modifier
                    .background(
                        Color(0xFF1F1E2B),
                        shape = RoundedCornerShape(20.dp))
                    .padding(12.dp)
                    .clickable{
                        onRetry()
                    }

                ,
                text = "Retry",
                style = TextStyle(
                    fontFamily = UrbanistFont,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color(0xDEFFFFFF)
                )
            )
        }

    }
}



@Preview
@Composable
fun ErrorP(modifier: Modifier = Modifier) {
    ErrorScreen(onRetry = {})
}