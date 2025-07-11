package com.example.newsapp.data.api.service

import com.example.newsapp.Utils.Constant.Companion.API_KEY
import com.example.newsapp.data.api.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServiceApi {

    @GET("sources")
    suspend fun getResults(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("country") country: String = "ru"

    ): NewsResponse
}