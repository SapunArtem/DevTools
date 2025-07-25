package com.example.movieapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieapp.presentation.components.movies.MoviesList
import com.example.movieapp.presentation.components.searchBar.SearchMovieBar
import com.example.movieapp.presentation.navigation.Screens
import com.example.movieapp.presentation.viewModel.MoviesViewModel

@Composable
fun HomeScreen(
    navController: NavController

){
    val viewModel : MoviesViewModel = viewModel()
    val movies by viewModel.moviesState.collectAsState()

    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        SearchMovieBar(
            viewModel = viewModel
        )
        Spacer(modifier = Modifier.height(30.dp))
        MoviesList(
            movies = movies,
            onMoviesClick = {moviesId ->
                navController.navigate(Screens.Details.createRoute(moviesId))
            },
            onLoadMore = {
                viewModel.loadNextPage()
            }
        )
    }


}