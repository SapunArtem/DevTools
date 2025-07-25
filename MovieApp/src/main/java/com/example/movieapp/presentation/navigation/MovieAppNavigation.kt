package com.example.movieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieapp.presentation.screens.DetailsScreen
import com.example.movieapp.presentation.screens.FavoriteScreen
import com.example.movieapp.presentation.screens.HomeScreen
import com.example.movieapp.presentation.screens.ProfileScreen
import com.example.movieapp.presentation.viewModel.FavoriteViewModel

@Composable
fun MovieAppNavigation(
    navController: NavHostController,
    modifier: Modifier
){
    val favoriteViewModel:FavoriteViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        modifier = modifier
    )
    {
        composable(Screens.Home.route) {
            HomeScreen(
                navController = navController
            )
        }
        composable(Screens.Favorite.route) {
            FavoriteScreen(
                navController = navController,
                favoriteViewModel = favoriteViewModel
            )
        }
        composable(Screens.Profile.route) {
            ProfileScreen()
        }
        composable(
            Screens.Details.route,
            arguments = listOf(
                navArgument("movieId"){type = NavType.IntType}
            )
        ) { backStackEntry->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: return@composable
            DetailsScreen(
                moviesId = movieId,
                favoriteViewModel = favoriteViewModel
            )
        }
    }
}