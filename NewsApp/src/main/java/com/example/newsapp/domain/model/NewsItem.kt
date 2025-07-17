package com.example.newsapp.domain.model

data class NewsItem(
    val id: String,
    val name: String,
    val url: String,
    val icon: String?,
    val priority: Int,
    val description: String,
    val category: List<String>,
    val language: List<String>,
    val country: List<String>,
    val total_article: Int,
    val last_fetch: String?
)
