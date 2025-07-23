package com.example.newsapp.data.api

import com.example.newsapp.data.api.service.NewsServiceApi
import retrofit2.Retrofit
import com.example.newsapp.utils.Constant.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Объект для создания и управления экземпляром Retrofit.
 * Реализует паттерн Singleton для повторного использования Retrofit.
 */
object RetrofitInstance {
    /**
     * Ленивая инициализация API сервиса.
     * Создается при первом обращении и сохраняется для последующих вызовов.
     */
    val okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(RetryInterceptor())
        .build()

    val api: NewsServiceApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsServiceApi::class.java)
    }
}
