package com.example.newsapp.data.api.models

/**
 * Модель ответа API для новостных источников.
 *
 * @property results Список новостных источников (Results)
 * @property status Статус запроса ("ok" при успехе, "error" при ошибке)
 * @property totalResults Общее количество доступных результатов
 */
data class NewsResponse(
    val results: List<Results>,
    val status: String,
    val totalResults: Int
)
