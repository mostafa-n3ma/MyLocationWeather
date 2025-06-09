package com.example.mylocationweather.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.unit.sp
import com.example.mylocationweather.R

val UrbanistFont = FontFamily(
    Font(resId = R.font.urbanist_light, weight = FontWeight.W300),      // Light
    Font(resId = R.font.urbanist_regular, weight = FontWeight.W400),    // Regular
    Font(resId = R.font.urbanist_medium, weight = FontWeight.W500),     // Medium
    Font(resId = R.font.urbanist_semi_bold, weight = FontWeight.W600),  // SemiBold
    Font(resId = R.font.urbanist_bold, weight = FontWeight.W700)        // Bold
)





// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)