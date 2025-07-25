package com.example.movieapp.presentation.components.movies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.movieapp.data.models.MovieItem

@Composable
fun MoviesList(
    movies : List<MovieItem>,
    onMoviesClick: (Int) -> Unit,
    onLoadMore:()->Unit

){
    val gridState = rememberLazyGridState()

    LaunchedEffect(Unit) {
        snapshotFlow {gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index}
            .collect{lastVisible ->
                if (lastVisible != null && lastVisible >= movies.size - 4){
                    onLoadMore()
                }
            }
    }


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = gridState
    ){
        items(movies){movieItem->
            MovieCard(
                movieItem = movieItem,
                onMovieClick = { onMoviesClick(movieItem.kinopoiskId) }
            )
        }
    }
    }
