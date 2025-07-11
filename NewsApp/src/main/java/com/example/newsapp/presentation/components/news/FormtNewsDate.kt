package com.example.newsapp.presentation.components.news

import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Форматирует дату новости из формата API в читаемый вид.
 *
 * @param dateString Строка с датой в формате "yyyy-MM-dd HH:mm:ss"
 * @return Отформатированная дата в виде "dd.MM.yyyy" или исходная строка при ошибке
 */
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