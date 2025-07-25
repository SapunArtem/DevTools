package com.example.movieapp.presentation.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.movieapp.domain.models.MovieItem

class FavoriteViewModel : ViewModel() {

    private val _favorites = mutableStateListOf<MovieItem>()
    val favorites: List<MovieItem> = _favorites


    fun addToFavorite(movieItem: MovieItem?) {
        movieItem ?: return
        val existing = _favorites.any { it.kinopoiskId == movieItem.kinopoiskId }
        if (existing) {
            _favorites.removeAll { it.kinopoiskId == movieItem.kinopoiskId }
        } else {
            _favorites.add(movieItem)
        }
    }

    fun isFavorite(movieId: Int): Boolean {
        return _favorites.any { it.kinopoiskId == movieId }
    }


}
