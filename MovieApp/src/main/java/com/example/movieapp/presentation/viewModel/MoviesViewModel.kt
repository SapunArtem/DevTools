package com.example.movieapp.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.repository.MoviesRepositoryImpl
import com.example.movieapp.domain.models.MovieItem
import com.example.movieapp.domain.useCases.GetMoviesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val getMoviesUseCase: GetMoviesUseCase = GetMoviesUseCase(
        MoviesRepositoryImpl()
    )
) : ViewModel() {

    private val _state = mutableStateOf(MoviesState())
    val state: State<MoviesState> = _state

    private val _movies = MutableStateFlow<List<MovieItem>>(emptyList())
    val movies: StateFlow<List<MovieItem>> = _movies


    private var searchJob: Job? = null
    private var currentPage = 1
    private var isMorePages = true
    private var _lastQuery = MutableStateFlow("")
    val lastQuery: StateFlow<String> = _lastQuery

    init {

        searchMovies("")
    }


    fun searchMovies(query: String) {
        _lastQuery.value = query
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            currentPage = 1
            isMorePages = true
            _state.value = _state.value.copy(isLoading = true, error = null)

            try {
                getMoviesUseCase(query, currentPage)
                    .onSuccess { response ->
                        _movies.value = response
                        isMorePages = response.isNotEmpty()
                    }
                    .onFailure { e ->
                        _state.value = _state.value.copy(error = e.message ?: "Unknown error")
                    }
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }

    fun loadNextPage() {
        if (_state.value.isLoading || !isMorePages) return

        searchJob = viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                getMoviesUseCase(_lastQuery.value, currentPage + 1)
                    .onSuccess { response ->
                        _movies.value += response
                        isMorePages = response.isNotEmpty()
                        currentPage++
                    }
                    .onFailure { e ->
                        _state.value = _state.value.copy(error = e.message ?: "Unknown error")
                    }
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }

    fun retry() {
        if (_movies.value.isEmpty()) {
            searchMovies(_lastQuery.value)
        } else {
            loadNextPage()
        }
    }

}

data class MoviesState(
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null
)





