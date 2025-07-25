package com.example.movieapp.presentation.components.movies

fun formatMovieTime(movieTime : Int?) : String{
    if (movieTime == null || movieTime <=0)return "-h:-m"
    val hours = movieTime / 60
    val mins = movieTime % 60
    return if (hours==0)"${mins}m" else "${hours}h:${mins}m"
}