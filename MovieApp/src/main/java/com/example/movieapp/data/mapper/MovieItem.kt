package com.example.movieapp.data.mapper

import com.example.movieapp.data.models.MovieItemDto
import com.example.movieapp.domain.models.MovieItem

fun MovieItemDto.toDomain() = MovieItem(
    kinopoiskId =kinopoiskId,
    imdbId = imdbId,
    nameRu = nameRu,
    nameEn = nameEn,
    nameOriginal = nameOriginal,
    countries = countries,
    genres = genres,
    ratingKinopoisk = ratingKinopoisk,
    ratingImdb = ratingImdb,
    year = year,
    type = type,
    posterUrl = posterUrl,
    posterUrlPreview = posterUrlPreview
)

fun MovieItem.toDto() = MovieItemDto(
    kinopoiskId = kinopoiskId,
    imdbId = imdbId,
    nameRu = nameRu,
    nameEn = nameEn,
    nameOriginal = nameOriginal,
    countries = countries,
    genres = genres,
    ratingKinopoisk = ratingKinopoisk,
    ratingImdb = ratingImdb,
    year = year,
    type = type,
    posterUrl = posterUrl,
    posterUrlPreview = posterUrlPreview
)

