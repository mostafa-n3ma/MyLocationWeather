package com.example.mylocationweather.pressentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mylocationweather.R

@Composable
 fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Image(
            modifier = Modifier.align(Alignment.Center).scale(2f),
            painter = painterResource(R.drawable.partly_cloudy),
            contentDescription = null
        )
        CircularProgressIndicator(Modifier.align(Alignment.Center).scale(2f))
    }
}


@Preview
@Composable
fun LoadingP(modifier: Modifier = Modifier) {
    LoadingScreen()
}