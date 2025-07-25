package com.example.movieapp.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.api.MoviesRepositoryImpl
import com.example.movieapp.domain.models.MovieItem
import com.example.movieapp.domain.useCases.GetMoviesUseCase
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
    val movies : StateFlow<List<MovieItem>> = _movies

    private var paginationState = PaginationState()
        private set



    init {
        loadMovies()
    }

    fun loadMovies(keyword:String = "", page: Int = 1) {
        if (_state.value.isLoading) return

        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true,
                error = null
            )
            try {
                val result = getMoviesUseCase(keyword, page)
                result.onSuccess { response->
                    _movies.value = if (page == 1){
                        response.items
                    } else {
                        _movies.value + response.items
                    }
                    paginationState = paginationState.copy(
                        lastQuery = keyword,
                        currentPage = page,
                        totalPages = response.totalPages,
                        isMorePages = page < response.totalPages
                    )
                }.onFailure { e->
                    _state.value = _state.value.copy(
                        error = e.message ?: "unknow error"
                    )
                }
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }

        }
    }
    fun loadNextPage() {
        if (_state.value.isLoading && paginationState.isMorePages) {
            loadMovies(
                keyword = paginationState.lastQuery,
                page = paginationState.currentPage + 1)
        }
    }

    fun searchMovies(query: String) {
        if (query != paginationState.lastQuery){
            loadMovies(query,1)
        }
    }
    private data class PaginationState(
        val lastQuery: String = "",
        val currentPage: Int = 1,
        val totalPages: Int = 1,
        val isMorePages : Boolean = true
    )
}
data class MoviesState(
    val isLoading : Boolean = false,
    val error: String? = null
)


