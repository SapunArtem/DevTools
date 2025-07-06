package com.example.note.domain.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Note(
    val id: Long? = null,
    val title: String,
    val content: String,
    val createdAt: Long = System.currentTimeMillis()
) {
    fun getFormatDate(): String {
        val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        return dateFormat.format(Date(createdAt))
    }
}
