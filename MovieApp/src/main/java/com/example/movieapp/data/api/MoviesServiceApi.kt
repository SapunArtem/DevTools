package com.example.movieapp.data.api

import com.example.movieapp.data.models.MovieDetails
import com.example.movieapp.data.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesServiceApi {
    @GET("films")
    suspend fun getMovies(
        @Query("keyword") keyword : String ,
        @Query("page") page : Int
    ): MovieResponse


    @GET("films/{id}")
    suspend fun getMovieDetails(
        @Path("id") id : Int
    ): MovieDetails
}