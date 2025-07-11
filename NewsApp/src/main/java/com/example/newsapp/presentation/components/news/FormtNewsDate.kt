package com.example.newsapp.presentation.components.news

import java.text.SimpleDateFormat
import java.util.Locale

fun formatNewsDate(dateString: String?): String {
    if (dateString.isNullOrEmpty()) {
        return " "
    }
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        outputFormat.format(date)
    } catch (e: Exception) {
        dateString
    }
}