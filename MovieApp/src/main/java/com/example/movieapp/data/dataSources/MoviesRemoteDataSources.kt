package com.example.movieapp.data.dataSources

import com.example.movieapp.data.models.MovieDetailsDto
import com.example.movieapp.data.models.MovieResponseDto
import com.example.movieapp.data.models.TrailerResponseDto

interface MoviesRemoteDataSources {
    suspend fun getMovies(keyword: String, page: Int): MovieResponseDto
    suspend fun getMovieDetails(id: Int): MovieDetailsDto
    suspend fun getTrailers(id: Int): TrailerResponseDto
}