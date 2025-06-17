package com.example.todomanagercompose

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val createdAt: String = SimpleDateFormat(
        "dd.MM.yyyy",
        Locale.getDefault()).format(Date())
)
