package com.example.movieapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.api.MoviesRepositoryImpl
import com.example.movieapp.data.models.MovieDetailsDto
import com.example.movieapp.domain.models.MovieDetails
import com.example.movieapp.domain.useCases.GetMovieDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase = GetMovieDetailsUseCase(
        MoviesRepositoryImpl()
    )
) : ViewModel() {


    private val _moviesDetails = MutableStateFlow<MovieDetails?>(null)
    val moviesDetails: StateFlow<MovieDetails?> = _moviesDetails



    fun loadMoviesDetails(id : Int){
        viewModelScope.launch {
            getMovieDetailsUseCase(id)
                .onSuccess { _moviesDetails.value = it }
        }
    }

}