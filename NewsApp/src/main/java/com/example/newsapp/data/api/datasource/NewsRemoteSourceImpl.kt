package com.example.newsapp.data.api.datasource

import com.example.newsapp.data.api.RetrofitInstance
import com.example.newsapp.data.api.models.NewsResponseDto
import com.example.newsapp.data.api.service.NewsServiceApi

/**
 * Реализация [NewsRemoteDataSource], использующая [NewsServiceApi] для получения данных.
 *
 * @property api API-сервис, предоставляющий данные о новостях.
 */
class NewsRemoteSourceImpl(
    private val api: NewsServiceApi = RetrofitInstance.api
) : NewsRemoteDataSource {
    /**
     * Получает список источников новостей с удалённого API.
     */
    override suspend fun getNewsSources(): NewsResponseDto {
        return api.getResults()
    }
}