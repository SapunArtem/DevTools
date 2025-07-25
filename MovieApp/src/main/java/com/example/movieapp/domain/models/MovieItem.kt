package com.example.movieapp.domain.models

import com.example.movieapp.data.models.CountryDto
import com.example.movieapp.data.models.GenreDto


data class MovieItem(
    val kinopoiskId: Int,
    val imdbId: String?,
    val nameRu: String?,
    val nameEn: String?,
    val nameOriginal: String?,
    val countries: List<CountryDto>,
    val genres: List<GenreDto>,
    val ratingKinopoisk: Double?,
    val ratingImdb: Double?,
    val year: Int?,
    val type: String?,
    val posterUrl: String?,
    val posterUrlPreview: String?
)
