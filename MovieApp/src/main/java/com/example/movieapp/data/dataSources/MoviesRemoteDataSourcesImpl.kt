package com.example.movieapp.data.dataSources

import com.example.movieapp.data.api.MoviesServiceApi
import com.example.movieapp.data.api.RetrofitInstance
import com.example.movieapp.data.models.MovieDetailsDto
import com.example.movieapp.data.models.MovieResponseDto
import com.example.movieapp.data.models.TrailerResponseDto

class MoviesRemoteDataSourcesImpl(
    private val api: MoviesServiceApi = RetrofitInstance.api
) : MoviesRemoteDataSources {
    override suspend fun getMovies(keyword: String, page: Int): MovieResponseDto {
        return api.getMovies(keyword, page)
    }

    override suspend fun getMovieDetails(id: Int): MovieDetailsDto {
        return api.getMovieDetails(id)
    }

    override suspend fun getTrailers(id: Int): TrailerResponseDto {
        return api.getTrailers(id)
    }
}