package com.example.movieapp.presentation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movieapp.data.mapper.toDto
import com.example.movieapp.presentation.components.movies.MoviesList
import com.example.movieapp.presentation.navigation.Screens
import com.example.movieapp.presentation.viewModel.FavoriteViewModel

@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel
){
    val favorites = favoriteViewModel.favorites

    MoviesList(
        movies = favorites.map { it.toDto() },
        onMoviesClick = {movieItem->
            navController.navigate(Screens.Details.createRoute(movieItem))
        },
        onLoadMore = {},
        isLoading = true,
        isLoadMore = true
    )
}