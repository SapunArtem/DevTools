package com.example.movieapp.data.models

data class MovieResponse(
    val total: Int,
    val totalPages: Int,
    val items: List<MovieItem>
)
