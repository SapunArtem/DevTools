package com.example.newsapp.data.mapper

import com.example.newsapp.data.api.models.NewsItemDto
import com.example.newsapp.domain.model.NewsItem

/**
 * Преобразует [NewsItemDto] в доменную модель [NewsItem].
 */
fun NewsItemDto.toDomain() = NewsItem(
    id = id,
    name = name,
    url = url,
    icon = icon,
    priority = priority,
    description = description,
    category = category,
    language = language,
    country = country,
    total_article = total_article,
    last_fetch = last_fetch
)

/**
 * Преобразует доменную модель [NewsItem] в DTO [NewsItemDto].
 */
fun NewsItem.toDto() = NewsItemDto(
    id = id,
    name = name,
    url = url,
    icon = icon,
    priority = priority,
    description = description,
    category = category,
    language = language,
    country = country,
    total_article = total_article,
    last_fetch = last_fetch
)