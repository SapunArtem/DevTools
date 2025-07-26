package com.example.movieapp.domain.useCases

import com.example.movieapp.domain.models.MovieDetails
import com.example.movieapp.domain.repository.MoviesRepository

class GetMovieDetailsUseCase(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(id: Int): Result<MovieDetails> {
        return repository.getMovieDetails(id)
    }
}
