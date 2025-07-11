package com.example.newsapp.data.api

import com.example.newsapp.data.api.models.NewsResponse

/**
 * Репозиторий для работы с новостными данными.
 * Обеспечивает абстракцию над API и управляет запросами к серверу.
 */
class NewsRepository {
    private val api = RetrofitInstance.api

    /**
     * Получает список новостных источников.
     *
     * @return Ответ API с новостными источниками (NewsResponse)
     */
    suspend fun getResults(): NewsResponse {
        return api.getResults()
    }
}