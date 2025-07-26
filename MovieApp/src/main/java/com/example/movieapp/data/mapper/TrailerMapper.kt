package com.example.movieapp.data.mapper

import com.example.movieapp.data.models.TrailerDto
import com.example.movieapp.domain.models.Trailer

fun TrailerDto.toDomain() = Trailer(
    url = url,
    name = name,
    site = site
)

fun Trailer.toDto() = TrailerDto(
    url = url,
    name = name,
    site = site
)