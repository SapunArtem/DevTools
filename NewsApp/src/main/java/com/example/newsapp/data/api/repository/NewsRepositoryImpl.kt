package com.example.newsapp.data.api.repository

import com.example.newsapp.data.api.datasource.NewsRemoteDataSource
import com.example.newsapp.data.api.datasource.NewsRemoteSourceImpl
import com.example.newsapp.data.mapper.toDomain
import com.example.newsapp.domain.model.NewsItem
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Репозиторий для работы с новостными данными.
 * Обеспечивает абстракцию над API и управляет запросами к серверу.
 */
class NewsRepositoryImpl(
    private val remoteDataSource: NewsRemoteDataSource = NewsRemoteSourceImpl()
) : NewsRepository{
    override suspend fun getNewsSources(country : String) : Result<List<NewsItem>>{
        return withContext(Dispatchers.IO){
            try {
                val response = remoteDataSource.getNewsSources(country)
                Result.success(response.results.map { it.toDomain() })
            }catch (e:Exception){
                Result.failure(e)
            }
        }
    }
}