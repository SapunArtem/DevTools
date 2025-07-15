package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.model.NewsItem
import com.example.newsapp.domain.repository.NewsRepository

class GetNewsSourcesUseCase(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(country : String = "ru"): Result<List<NewsItem>>{
        return repository.getNewsSources(country)
    }
}