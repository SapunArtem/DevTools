package com.example.movieapp.domain.models

data class Movies(
    val movies: List<MovieItem>,
    val currentPage: Int,
    val totalPages: Int
)
