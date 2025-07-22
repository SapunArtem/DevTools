package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.NewsItem

/**
 * Репозиторий для получения данных о новостях.
 */
interface NewsRepository {
    /**
     * Получает список всех источников новостей.
     *
     * @return [Result] со списком [NewsItem] или ошибкой.
     */
    suspend fun getNewsSources(): Result<List<NewsItem>>
}
