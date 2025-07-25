package com.example.movieapp.data.api

import com.example.movieapp.data.models.MovieDetailsDto
import com.example.movieapp.data.models.MovieResponseDto

class MoviesRemoteDataSourcesImpl(
    private val api: MoviesServiceApi = RetrofitInstance.api
) : MoviesRemoteDataSources
{
    override suspend fun getMovies(keyword:String,page:Int): MovieResponseDto {
        return api.getMovies(keyword, page)
    }

    override suspend fun getMovieDetails(id:Int): MovieDetailsDto {
        return api.getMovieDetails(id)
    }
}