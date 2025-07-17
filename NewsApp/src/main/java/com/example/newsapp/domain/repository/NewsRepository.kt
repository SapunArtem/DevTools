package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.NewsItem

interface NewsRepository {
    suspend fun getNewsSources() : Result<List<NewsItem>>
}