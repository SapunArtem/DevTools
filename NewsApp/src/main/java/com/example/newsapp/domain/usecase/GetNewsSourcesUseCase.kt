package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.model.NewsItem
import com.example.newsapp.domain.repository.NewsRepository

/**
 * UseCase для получения всех источников новостей.
 *
 * @property repository Репозиторий новостей.
 */
class GetNewsSourcesUseCase(
    private val repository: NewsRepository
) {
    /**
     * Выполняет запрос на получение списка источников новостей.
     *
     * @return [Result] со списком [NewsItem] или ошибкой.
     */
    suspend operator fun invoke(): Result<List<NewsItem>> {
        return repository.getNewsSources()
    }
}