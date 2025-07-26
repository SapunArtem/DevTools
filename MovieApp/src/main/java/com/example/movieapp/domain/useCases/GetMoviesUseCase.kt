package com.example.movieapp.domain.useCases

import com.example.movieapp.domain.models.MovieItem
import com.example.movieapp.domain.repository.MoviesRepository

class GetMoviesUseCase(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(keyword: String, page: Int): Result<List<MovieItem>> {
        return repository.getMovies(keyword, page)
    }
}
