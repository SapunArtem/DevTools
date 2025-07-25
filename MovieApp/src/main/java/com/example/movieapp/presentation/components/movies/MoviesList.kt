package com.example.movieapp.presentation.components.movies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.movieapp.data.models.MovieItemDto
import com.example.movieapp.domain.models.MovieItem
import com.example.movieapp.presentation.ui.theme.Orange

@Composable
fun MoviesList(
    movies : List<MovieItemDto>,
    onMoviesClick: (Int) -> Unit,
    onLoadMore:()->Unit,
    isLoading : Boolean,
    isLoadMore : Boolean

){
    val gridState = rememberLazyGridState()

    LaunchedEffect(gridState) {
        snapshotFlow {gridState.layoutInfo}
            .collect{layoutInfo ->
                if (isLoadMore && !isLoading){
                    val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                    if (lastVisibleItem != null &&
                            lastVisibleItem.index >= layoutInfo.totalItemsCount - 5){
                                onLoadMore()
                    }
                }
            }
    }


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = gridState
    ){
        items(movies){movieItem->
            MovieCard(
                movieItemDto = movieItem,
                onMovieClick = { onMoviesClick(movieItem.kinopoiskId) }
            )
        }
        if (isLoading && isLoadMore){
            item {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator(color = Orange)
                }
            }
        }
    }
    }
