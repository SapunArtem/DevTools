package com.example.newsapp.presentation.components.bottom_nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import com.example.newsapp.R
import com.example.newsapp.presentation.navigation.Screen

object BottomNavList {
    val bottomNavItemsList = listOf(
        BottomItem(
            title = R.string.news,
            icon = Icons.Filled.Home,
            route = Screen.NewsScreen.route
        ),
        BottomItem(
            title = R.string.settings,
            icon = Icons.Filled.Settings,
            route = Screen.SettingsScreen.route
        )
    )
}