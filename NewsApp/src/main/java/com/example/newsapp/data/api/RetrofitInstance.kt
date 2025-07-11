package com.example.newsapp.data.api

import com.example.newsapp.data.api.service.NewsServiceApi
import retrofit2.Retrofit
import com.example.newsapp.Utils.Constant.Companion.BASE_URL
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: NewsServiceApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsServiceApi::class.java)
    }
}
