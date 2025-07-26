package com.example.movieapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp.data.mapper.toMovieItemDto
import com.example.movieapp.presentation.components.details.MovieDetailsContent
import com.example.movieapp.presentation.components.error.EmptyState
import com.example.movieapp.presentation.components.error.ErrorMessage
import com.example.movieapp.presentation.ui.theme.Orange
import com.example.movieapp.presentation.ui.theme.White
import com.example.movieapp.presentation.viewModel.DetailsViewModel
import com.example.movieapp.presentation.viewModel.FavoriteViewModel
import com.example.movieapp.R

@Composable
fun DetailsScreen(
    moviesId: Int,
    favoriteViewModel: FavoriteViewModel = viewModel(),
    detailsViewModel: DetailsViewModel = viewModel()
) {
    val context = LocalContext.current
    val trailerError by detailsViewModel.trailerError.collectAsState()

    val movieDetails by detailsViewModel.moviesDetails.collectAsState()
    val isLoading by detailsViewModel.isLoading.collectAsState()
    val error by detailsViewModel.error.collectAsState()



    LaunchedEffect(moviesId) {
        detailsViewModel.loadMoviesDetails(moviesId)
    }

    if (trailerError != null) {
        AlertDialog(
            onDismissRequest = { detailsViewModel.setTrailerError(null) },
            title = { Text(stringResource(R.string.error), color = Orange) },
            text = { Text(trailerError!!, color = White) },
            confirmButton = {
                Button(
                    onClick = { detailsViewModel.setTrailerError(null) },
                    colors = ButtonDefaults.buttonColors(containerColor = Orange)
                ) {
                    Text(stringResource(R.string.ok), color = White)
                }
            },
            containerColor = MaterialTheme.colorScheme.background
        )
    }
    when {
        isLoading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Orange)
            }
        }

        error != null -> ErrorMessage(error!!) { detailsViewModel.retry(moviesId) }
        movieDetails == null -> EmptyState(text = stringResource(R.string.movie_etails_not_available))
        else -> {
            movieDetails?.let { item ->
                val isFavorite by favoriteViewModel
                    .isFavoriteFlow(item.kinopoiskId)
                    .collectAsState(initial = favoriteViewModel.isFavorite(item.kinopoiskId))
                MovieDetailsContent(
                    item = item,
                    isFavorite = isFavorite,
                    onFavoriteClick = { favoriteViewModel.addToFavorite(item.toMovieItemDto()) },
                    onPlayClick = { detailsViewModel.loadTrailer(context, moviesId) }
                )

            }
        }
    }


}

