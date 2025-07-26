package com.example.movieapp.data.repository

import com.example.movieapp.data.dataSources.MoviesRemoteDataSources
import com.example.movieapp.data.dataSources.MoviesRemoteDataSourcesImpl
import com.example.movieapp.data.mapper.toDomain
import com.example.movieapp.domain.models.MovieDetails
import com.example.movieapp.domain.models.MovieItem
import com.example.movieapp.domain.models.Trailer
import com.example.movieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(
    private val remoteDataSources: MoviesRemoteDataSources = MoviesRemoteDataSourcesImpl()
) : MoviesRepository {

    override suspend fun getMovies(keyword: String, page: Int): Result<List<MovieItem>> {
        return withContext(Dispatchers.IO) {
            try {
                val dto = remoteDataSources.getMovies(keyword, page)
                Result.success(dto.items.map { it.toDomain() })
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun getMovieDetails(id: Int): Result<MovieDetails> {
        return withContext(Dispatchers.IO) {
            try {
                val dto = remoteDataSources.getMovieDetails(id)
                Result.success(dto.toDomain())
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun getTrailers(id: Int): Result<List<Trailer>> {
        return withContext(Dispatchers.IO) {
            try {
                val dto = remoteDataSources.getTrailers(id)
                Result.success(dto.items.map { it.toDomain() })
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}