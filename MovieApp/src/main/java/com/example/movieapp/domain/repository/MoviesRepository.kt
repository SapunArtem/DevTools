package com.example.movieapp.domain.repository

import com.example.movieapp.domain.models.MovieDetails
import com.example.movieapp.domain.models.MovieItem
import com.example.movieapp.domain.models.Trailer

interface MoviesRepository {
    suspend fun getMovies(keyword: String, page: Int): Result<List<MovieItem>>
    suspend fun getMovieDetails(id: Int): Result<MovieDetails>
    suspend fun getTrailers(id: Int): Result<List<Trailer>>
}