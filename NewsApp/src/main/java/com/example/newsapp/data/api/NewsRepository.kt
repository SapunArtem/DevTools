package com.example.newsapp.data.api

import com.example.newsapp.data.api.models.NewsResponse

class NewsRepository {
    private val api = RetrofitInstance.api

    suspend fun getResults(): NewsResponse {
        return api.getResults()
    }
}