package com.example.newsapp.data.api.datasource

import com.example.newsapp.data.api.RetrofitInstance
import com.example.newsapp.data.api.models.NewsResponseDto
import com.example.newsapp.data.api.service.NewsServiceApi

class NewsRemoteSourceImpl(
    private val api: NewsServiceApi = RetrofitInstance.api
) : NewsRemoteDataSource{
    override suspend fun getNewsSources(country: String): NewsResponseDto {
        return api.getResults(country)
    }
}