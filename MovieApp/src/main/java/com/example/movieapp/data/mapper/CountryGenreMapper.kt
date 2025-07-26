package com.example.movieapp.data.mapper

import com.example.movieapp.data.models.CountryDto
import com.example.movieapp.data.models.GenreDto
import com.example.movieapp.domain.models.Country
import com.example.movieapp.domain.models.Genre

fun GenreDto.toDomain() = Genre(genre = genre)
fun Genre.toDto() = GenreDto(genre = genre)


fun CountryDto.toDomain() = Country(country = country)
fun Country.toDto() = CountryDto(country = country)