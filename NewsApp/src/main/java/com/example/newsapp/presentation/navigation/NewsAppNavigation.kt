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
import com.example.newsapp.presentation.viewModel.SettingsViewModel

/**
 * Screen - Класс, определяющий маршруты экранов приложения.
 *
 * @property route Базовый маршрут экрана
 */
sealed class Screen(val route: String) {
    object NewsScreen : Screen("news_screen")
    object SettingsScreen : Screen("settings_screen")
    object DetailsScreen : Screen("details_screen/{newsId}") {
        fun createRoute(newsId: String) = "details_screen/$newsId"
    }
}

/**
 * Навигация приложения новостей с использованием NavHostController.
 *
 * @param navController контроллер навигации
 * @param settingsViewModel ViewModel для экрана настроек
 * @param modifier Modifier для настройки внешнего вида NavHost
 */
@Composable
fun NewsAppNavigation(
    navController: NavHostController,
    settingsViewModel: SettingsViewModel,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.NewsScreen.route,
        modifier = modifier
    ) {
        // Экран новостей
        composable(Screen.NewsScreen.route) {
            NewsScreen(
                onNewsClick = { newsId ->
                    navController.navigate(Screen.DetailsScreen.createRoute(newsId))
                }
            )
        }
        // Экран настроек
        composable(Screen.SettingsScreen.route) {
            SettingsScreen(viewModel = settingsViewModel)
        }
        // Экран деталей с параметром newsId
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