package com.example.newsapp.data.api.service

import com.example.newsapp.data.api.models.NewsResponse
import com.example.newsapp.utils.Constant
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Интерфейс API для работы с новостными источниками.
 * Определяет endpoints и параметры запросов к News API.
 */
interface NewsServiceApi {

    /**
     * Получает список новостных источников.
     *
     * @param apiKey Ключ API (по умолчанию берется из Constants)
     * @param country Страна для фильтрации (по умолчанию "ru" - Россия)
     * @return Ответ API с новостными источниками (NewsResponse)
     */
    @GET("sources")
    suspend fun getResults(
        @Query("apiKey") apiKey: String = Constant.API_KEY,
        @Query("country") country: String = "ru"

    ): NewsResponse
}