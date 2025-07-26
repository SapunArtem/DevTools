package com.example.movieapp.presentation.components.movies

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.movieapp.data.models.MovieItemDto
import com.example.movieapp.presentation.ui.theme.Orange

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieCard(
    movieItemDto: MovieItemDto,
    onMovieClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clickable(onClick = { onMovieClick() })
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp)
                .border(
                    width = 2.dp,
                    color = Orange,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            GlideImage(
                model = movieItemDto.posterUrlPreview,
                contentDescription = movieItemDto.nameRu,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxSize()
            )
        }


    }
}