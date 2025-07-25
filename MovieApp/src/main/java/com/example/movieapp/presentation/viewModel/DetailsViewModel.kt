package com.example.movieapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.api.MoviesRepository
import com.example.movieapp.data.models.MovieDetails
import com.example.movieapp.data.models.MovieItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    private val repository = MoviesRepository()

    private val _moviesDetails = MutableStateFlow<MovieDetails?>(null)
    val moviesDetails: StateFlow<MovieDetails?> = _moviesDetails



    fun loadMoviesDetails(moviesId : Int){
        viewModelScope.launch {
            val movieDetail = repository.getMovieDetails(moviesId)
            _moviesDetails.value = movieDetail
        }
    }

}