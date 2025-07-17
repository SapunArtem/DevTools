package com.example.newsapp.data.api.datasource

import com.example.newsapp.data.api.models.NewsResponseDto

interface NewsRemoteDataSource {
    suspend fun getNewsSources(): NewsResponseDto
}