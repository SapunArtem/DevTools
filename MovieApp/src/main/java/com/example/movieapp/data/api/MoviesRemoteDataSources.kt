package com.example.movieapp.data.api

import com.example.movieapp.data.models.MovieDetailsDto
import com.example.movieapp.data.models.MovieResponseDto

interface MoviesRemoteDataSources {
    suspend fun getMovies(keyword: String,page:Int): MovieResponseDto
    suspend fun getMovieDetails(id : Int): MovieDetailsDto
}