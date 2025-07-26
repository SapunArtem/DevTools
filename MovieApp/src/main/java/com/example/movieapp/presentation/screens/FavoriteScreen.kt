package com.example.movieapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.data.mapper.toDto
import com.example.movieapp.presentation.components.error.EmptyState
import com.example.movieapp.presentation.components.error.ErrorMessage
import com.example.movieapp.presentation.components.movies.MovieCard
import com.example.movieapp.presentation.navigation.Screens
import com.example.movieapp.presentation.viewModel.FavoriteViewModel

@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel
) {
    val favorites by favoriteViewModel.favorites.collectAsState()
    val error by favoriteViewModel.error.collectAsState()


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            error != null -> ErrorMessage(error!!) { }
            favorites.isEmpty() -> EmptyState(stringResource(R.string.there_are_no_favorites))
            else -> LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                items(
                    items = favorites,
                ) { movieItem ->
                    MovieCard(
                        movieItemDto = movieItem.toDto(),
                        onMovieClick = {
                            navController.navigate(
                                Screens.Details.createRoute(
                                    movieItem.kinopoiskId
                                )
                            )
                        }
                    )
                }
            }
        }
    }


}