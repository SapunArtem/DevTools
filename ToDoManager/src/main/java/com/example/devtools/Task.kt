package com.example.devtools

import java.util.Date

data class Task(
    val id: Int,
    var title: String,
    var isCompleted: Boolean = false,
    val createdAt: Date = Date()
)
