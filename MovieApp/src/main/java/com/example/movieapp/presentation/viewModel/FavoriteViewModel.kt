package com.example.movieapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.models.MovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {

    private val _favorites = MutableStateFlow<List<MovieItem>>(emptyList())
    val favorites: StateFlow<List<MovieItem>> = _favorites.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()


    fun addToFavorite(movieItem: MovieItem?) {
        viewModelScope.launch {
            try {
                movieItem ?: throw IllegalArgumentException("movie item is null")

                val current = _favorites.value.toMutableList()
                val existing = current.any { it.kinopoiskId == movieItem.kinopoiskId }

                if (existing) {
                    current.removeAll { it.kinopoiskId == movieItem.kinopoiskId }
                } else {
                    current.add(movieItem)
                }

                _favorites.value = current
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message ?: "Error updating favorites"
            }
        }
    }

    fun isFavorite(movieId: Int): Boolean {
        return _favorites.value.any { it.kinopoiskId == movieId }
    }

    fun isFavoriteFlow(movieId: Int): Flow<Boolean> {
        return favorites.map { favoritesList ->
            favoritesList.any { it.kinopoiskId == movieId }
        }
    }


}
