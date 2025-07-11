package com.example.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsapp.presentation.screens.DetailsScreen
import com.example.newsapp.presentation.screens.NewsScreen
import com.example.newsapp.presentation.screens.SettingsScreen

sealed class Screen(val route: String) {
    object NewsScreen : Screen("news_screen")
    object SettingsScreen : Screen("settings_screen")
    object DetailsScreen : Screen("details_screen/{newsId}") {
        fun createRoute(newsId: String) = "details_screen/$newsId"
    }
}

@Composable
fun NewsAppNavigation(
    navController: NavHostController,
    currentLanguage: String,
    setLanguage: (String) -> Unit,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.NewsScreen.route,
        modifier = modifier
    ) {
        composable(Screen.NewsScreen.route) {
            NewsScreen(
                navController = navController
            )
        }
        composable(Screen.SettingsScreen.route) {
            SettingsScreen(
                currentLanguage = currentLanguage,
                setLanguage = setLanguage
            )
        }
        composable(
            Screen.DetailsScreen.route,
            arguments = listOf(
                navArgument("newsId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val newsId = backStackEntry.arguments?.getString("newsId") ?: ""
            DetailsScreen(
                newsId = newsId
            )
        }
    }
}