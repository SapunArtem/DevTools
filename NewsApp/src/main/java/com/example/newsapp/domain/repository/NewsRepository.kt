package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.NewsItem

interface NewsRepository {
    suspend fun getNewsSources(country : String) : Result<List<NewsItem>>
}