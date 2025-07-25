package com.example.movieapp.presentation.components.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.movieapp.data.models.GenreDto
import com.example.movieapp.presentation.ui.theme.Orange
import com.example.movieapp.presentation.ui.theme.White

@Composable
fun GenreList(genres: List<GenreDto>){
    Row (
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ){
        genres.forEach { genre ->
            Box(modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Orange)
                .padding(8.dp)
            ){
                Text(
                    text = genre.genre,
                    color = White
                )
            }


        }
    }
}