package com.example.newsapp.data.api.datasource

import com.example.newsapp.data.api.models.NewsResponseDto

/**
 * Интерфейс для удалённого источника данных новостей.
 */
interface NewsRemoteDataSource {
    /**
     * Получает список источников новостей с удалённого API.
     *
     * @return [NewsResponseDto] — DTO объект, содержащий данные о новостях.
     */
    suspend fun getNewsSources(): NewsResponseDto
}