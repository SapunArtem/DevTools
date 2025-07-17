package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.model.NewsItem
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * UseCase для получения подробностей по конкретному источнику новостей.
 *
 * @property repository Репозиторий новостей.
 */
class GetNewsDetailsUseCase(
    private val repository: NewsRepository
) {
    /**
     * Получает [NewsItem] по его [newsId].
     *
     * @param newsId Идентификатор источника.
     * @return [Result] с найденным [NewsItem] или ошибкой.
     */
    suspend operator fun invoke(newsId: String): Result<NewsItem> {
        return withContext(Dispatchers.IO) {
            try {
                val allNews = repository.getNewsSources().getOrThrow()
                allNews.firstOrNull { it.id == newsId }?.let { newsItem ->
                    Result.success(newsItem)
                } ?: Result.failure(Exception("News with id $newsId not found"))
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}