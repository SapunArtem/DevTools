package com.example.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
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
 * NewsAppNavigation - Основной навигационный компонент приложения.
 *
 * @param navController Контроллер навигации
 * @param currentLanguage Текущий язык приложения
 * @param setLanguage Функция для изменения языка
 * @param modifier Модификатор компоновки
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
            NewsScreen()
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