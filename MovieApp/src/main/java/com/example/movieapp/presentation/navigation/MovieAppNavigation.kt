package com.example.movieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieapp.presentation.screens.DetailsScreen
import com.example.movieapp.presentation.screens.FavoriteScreen
import com.example.movieapp.presentation.screens.HomeScreen
import com.example.movieapp.presentation.screens.ProfileScreen

@Composable
fun MovieAppNavigation(
    navController: NavHostController,
    modifier: Modifier
){
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        modifier = modifier
    )
    {
        composable(Screens.Home.route) {
            HomeScreen()
        }
        composable(Screens.Favorite.route) {
            FavoriteScreen()
        }
        composable(Screens.Profile.route) {
            ProfileScreen()
        }
        composable(
            Screens.Details.route,
            arguments = listOf(
                navArgument("movieId"){type = NavType.StringType}
            )
        ) { backStackEntry->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            DetailsScreen()
        }
    }
}