package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.model.NewsItem
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetNewsDetailsUseCase(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(newsId : String) : Result<NewsItem>{
        return withContext(Dispatchers.IO){
             try {
                val allNews = repository.getNewsSources().getOrThrow()
                allNews.firstOrNull{it.id == newsId}?.let { newsItem ->
                    Result.success(newsItem)
                } ?: Result.failure(Exception("News with id $newsId not found"))
            }catch (e : Exception){
                Result.failure(e)
            }
        }
    }
}