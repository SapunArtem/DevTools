package com.example.movieapp.data.models

data class MovieResponseDto(
    val total: Int,
    val totalPages: Int,
    val items: List<MovieItemDto>
)
