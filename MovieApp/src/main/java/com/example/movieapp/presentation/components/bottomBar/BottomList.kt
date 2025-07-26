package com.example.movieapp.presentation.components.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import com.example.movieapp.R
import com.example.movieapp.presentation.navigation.Screens

object BottomList {
    val bottomItemsList = listOf(
        BottomItem(
            title = R.string.home,
            icon = Icons.Filled.Home,
            route = Screens.Home.route
        ),
        BottomItem(
            title = R.string.favorite,
            icon = Icons.Filled.Favorite,
            route = Screens.Favorite.route
        ),
        BottomItem(
            title = R.string.profile,
            icon = Icons.Filled.Person,
            route = Screens.Profile.route
        )
    )
}