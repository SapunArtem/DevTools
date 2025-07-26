package com.example.movieapp.domain.useCases

import com.example.movieapp.domain.models.Trailer
import com.example.movieapp.domain.repository.MoviesRepository

class GetTrailerUseCase(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(id: Int): Result<List<Trailer>> {
        return repository.getTrailers(id)
    }
}