package com.example.movieapp.presentation.viewModel

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.repository.MoviesRepositoryImpl
import com.example.movieapp.domain.models.MovieDetails
import com.example.movieapp.domain.useCases.GetMovieDetailsUseCase
import com.example.movieapp.domain.useCases.GetTrailerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.core.net.toUri

class DetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase = GetMovieDetailsUseCase(
        MoviesRepositoryImpl()
    ),
    private val getTrailerUseCase: GetTrailerUseCase = GetTrailerUseCase(
        MoviesRepositoryImpl()
    )
) : ViewModel() {


    private val _moviesDetails = MutableStateFlow<MovieDetails?>(null)
    val moviesDetails: StateFlow<MovieDetails?> = _moviesDetails

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _trailerUrl = MutableStateFlow<String?>(null)
    val trailerUrl: StateFlow<String?> = _trailerUrl.asStateFlow()

    private val _trailerError = MutableStateFlow<String?>(null)
    val trailerError: StateFlow<String?> = _trailerError.asStateFlow()


    fun loadMoviesDetails(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            getMovieDetailsUseCase(id)
                .onSuccess {
                    _moviesDetails.value = it
                    _error.value = null
                }
                .onFailure { e ->
                    _error.value = e.message ?: "Faild to load movie details"
                    _moviesDetails.value = null
                }
            _isLoading.value = false
        }
    }

    fun retry(id: Int) {
        loadMoviesDetails(id)
    }

    fun loadTrailer(context: Context, movieId: Int) {
        viewModelScope.launch {
            _trailerError.value = null

            getTrailerUseCase(movieId)
                .onSuccess { trailers ->
                    val trailer =
                        trailers.firstOrNull { it.site == "YOUTUBE" } ?: trailers.firstOrNull()
                    _trailerUrl.value = trailer?.url
                    if (trailer != null) {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, trailer.url.toUri())
                            context.startActivity(intent)
                        } catch (e: ActivityNotFoundException) {
                            _trailerError.value = "Не удалось открыть трейлер"
                        }
                    } else {
                        _trailerError.value = "Трейлеры не найдены"
                    }
                }
                .onFailure { e ->
                    _trailerError.value = e.message ?: "Ошибка воспроизведения трейлера"
                }
        }
    }

    fun setTrailerError(message: String?) {
        _trailerError.value = message
    }

}