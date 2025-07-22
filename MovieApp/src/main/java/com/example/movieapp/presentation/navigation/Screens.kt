package com.example.movieapp.presentation.navigation

sealed class Screens(val route: String) {
    object Home : Screens("home_screen")
    object Favorite : Screens("favorite_screen")
    object Profile : Screens("profile_screen")
    object Details : Screens("details_screen/{movieId}"){
        fun createRoute(movieId: String) = "details_screen/$movieId"
    }
}