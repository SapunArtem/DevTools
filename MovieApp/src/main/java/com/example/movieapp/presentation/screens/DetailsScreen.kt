package com.example.movieapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.movieapp.data.mapper.toMovieItemDto
import com.example.movieapp.presentation.components.details.Rating
import com.example.movieapp.presentation.components.movies.GenreList
import com.example.movieapp.presentation.components.movies.formatMovieTime
import com.example.movieapp.presentation.ui.theme.GrayUnselected
import com.example.movieapp.presentation.ui.theme.Orange
import com.example.movieapp.presentation.ui.theme.White
import com.example.movieapp.presentation.viewModel.DetailsViewModel
import com.example.movieapp.presentation.viewModel.FavoriteViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsScreen(
    moviesId : Int,
    favoriteViewModel: FavoriteViewModel)
{
    val viewModel : DetailsViewModel = viewModel()
    val movieDetails by viewModel.moviesDetails.collectAsState()



    LaunchedEffect(key1 = moviesId) {
        viewModel.loadMoviesDetails(moviesId)
    }

    movieDetails?.let { item ->
        val isFavorite = remember(item) {
            derivedStateOf { favoriteViewModel.isFavorite(item.kinopoiskId) }
        }
        Card(
            modifier = Modifier
                .fillMaxSize(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                ) {
                    GlideImage(
                        model = item.posterUrl,
                        contentDescription = "BackImage",
                        alignment = Alignment.Center,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                    )

                }
                Spacer(modifier = Modifier.height(30.dp))
                Column (
                    modifier = Modifier
                        .padding(10.dp)
                ){

                    Rating (
                        name = "IMDB",
                        rating =  item.ratingImdb ,
                        count = item.ratingImdbVoteCount
                    )
                    Rating(
                        name = "Kinopoisk",
                        rating =  item.ratingKinopoisk ,
                        count = item.ratingKinopoiskVoteCount
                    )

                    Text(
                        text = item.nameEn ?: item.nameRu ?: item.nameOriginal ?: "",
                        color = White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Orange)
                                .padding(8.dp)
                        ) {
                            Text(
                                text = "${ item.year }",
                                color = White
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Orange)
                                .padding(8.dp)
                        ) {
                            Text(
                                text = formatMovieTime(item.filmLength) ,
                                color = White
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    GenreList(genres = item.genres)

                    Spacer(modifier = Modifier.height(30.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .height(50.dp)
                                .padding(start = 10.dp)
                                .weight(0.4f),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Orange
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Filled.PlayArrow,
                                contentDescription = "Play",
                                tint = White
                            )
                            Text(
                                text = "Play Now",
                                color = White
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Box(
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .size(50.dp)
                        ) {
                            IconButton(
                                onClick = {favoriteViewModel.addToFavorite(item.toMovieItemDto())}
                            )
                            {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Icon",
                                    tint = if (isFavorite.value) Orange else White,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .align(Alignment.Center)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = "Description",
                        color = White,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = item.description ?: "No description for this title",
                        color = GrayUnselected
                    )
                }
            }

            }


        }

    }

