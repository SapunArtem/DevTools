package com.example.movieapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieapp.data.mapper.toDto
import com.example.movieapp.presentation.components.error.EmptyState
import com.example.movieapp.presentation.components.error.ErrorMessage
import com.example.movieapp.presentation.components.movies.MoviesList
import com.example.movieapp.presentation.components.searchBar.SearchMovieBar
import com.example.movieapp.presentation.navigation.Screens
import com.example.movieapp.presentation.ui.theme.Orange
import com.example.movieapp.presentation.viewModel.MoviesViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MoviesViewModel = viewModel()

){
    val movies by viewModel.movies.collectAsState()
    val state by viewModel.state

    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        SearchMovieBar{query->
            viewModel.searchMovies(query)
        }
        Spacer(modifier = Modifier.height(30.dp))
        when{
            state.isLoading ->{
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator(color = Orange)
                }
            }
            state.error != null ->{
                ErrorMessage(state.error!!){
                    viewModel.loadMovies()
                }
            }
            movies.isEmpty()->{
                EmptyState()
            }

            else -> {
                MoviesList(
                    movies = movies.map { it.toDto() },
                    onMoviesClick = {moviesId ->
                        navController.navigate(Screens.Details.createRoute(moviesId))
                    },
                    onLoadMore = {
                        viewModel.loadNextPage()
                    },
                    isLoading = state.isLoading,
                    isLoadMore = true
                )
            }
        }
    }

}