package com.example.mylocationweather.pressentation.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
fun getDayName(input: String): String {
    // Parse the input string into a LocalDate (ISO format: "yyyy-MM-dd")
    val date = LocalDate.parse(input, DateTimeFormatter.ISO_DATE)

    // Get the day of the week and format it as a full name (e.g., "Monday")
    return date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
}


@RequiresApi(Build.VERSION_CODES.O)
fun formatTime(input: String): String {
    // Parse the input string into LocalDateTime
    val dateTime = LocalDateTime.parse(input, DateTimeFormatter.ISO_DATE_TIME)

    // Format to extract only "HH:mm" (24-hour format)
    return dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
}