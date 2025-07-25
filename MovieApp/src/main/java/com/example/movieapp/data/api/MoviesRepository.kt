package com.example.movieapp.data.api

import com.example.movieapp.data.models.MovieDetails
import com.example.movieapp.data.models.MovieResponse

class MoviesRepository {
    private val api = RetrofitInstance.api

    suspend fun getMovies(keyword : String,page: Int): MovieResponse{
        return api.getMovies(keyword,page)
    }


    suspend fun getMovieDetails(id:Int) : MovieDetails{
        return api.getMovieDetails(id)
    }
}