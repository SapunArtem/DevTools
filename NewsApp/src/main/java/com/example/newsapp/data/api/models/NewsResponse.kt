package com.example.newsapp.data.api.models

data class NewsResponse(
    val results: List<Results>,
    val status: String,
    val totalResults: Int
)
