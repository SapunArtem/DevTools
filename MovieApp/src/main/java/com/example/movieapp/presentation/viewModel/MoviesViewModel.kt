package com.example.movieapp.presentation.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.asIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.api.MoviesRepository
import com.example.movieapp.data.models.MovieItem
import com.example.movieapp.data.models.MovieResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    private val repository = MoviesRepository()

    private val _moviesState = MutableStateFlow<List<MovieItem>>(emptyList())
    val moviesState: StateFlow<List<MovieItem>> = _moviesState

    private val _totalPages = mutableIntStateOf(1)

    private val _currentPage = mutableIntStateOf(1)

    private val _isLoading = mutableStateOf(false)

    private var lastQuery : String = ""


    init {
        loadMovies()
    }

    fun loadMovies(keyword:String = "", page: Int = 1) {
        viewModelScope.launch {
            _isLoading.value = true
            lastQuery = keyword
            val results = repository.getMovies(keyword, page)
            _moviesState.value = results.items
            _totalPages.intValue = results.totalPages
            _currentPage.intValue = page
            _isLoading.value = false

        }
    }
    fun loadNextPage() {
        if (_isLoading.value || _currentPage.intValue >= _totalPages.intValue) return

        viewModelScope.launch {
            _isLoading.value = true
            val nextPage = _currentPage.intValue + 1
            val result = repository.getMovies(lastQuery, nextPage)
            _moviesState.value += result.items
            _currentPage.intValue = nextPage
            _isLoading.value = false
        }
    }

    fun searchMovies(query: String) {
        loadMovies(keyword = query, page = 1)
    }
}


