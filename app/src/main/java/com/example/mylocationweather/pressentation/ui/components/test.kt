package com.example.mylocationweather.pressentation.ui.components



import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.ktor.websocket.Frame.Text


@Composable
fun AnimatedBox() {
    var isAnimated by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0f) }

    val animatedProgress = animateFloatAsState(
        targetValue = if (isAnimated) 1f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "progress animation"
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Box(
            Modifier
                .size(100.dp)
                .background(Color.Red)
                .alpha(animatedProgress.value)
        )
    }

    // Button to toggle animation
    Button(onClick = { isAnimated = !isAnimated }) {
        Text("Toggle Animation")
    }
}

@Preview
@Composable
fun TypeNotPresentException(modifier: Modifier = Modifier) {
    AnimatedBox()
}