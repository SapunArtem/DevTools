package com.example.newsapp.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.newsapp.presentation.components.bottom_nav.BottomBar
import com.example.newsapp.presentation.components.settings.LocalizationManager
import com.example.newsapp.presentation.components.top_bar.TopBar
import com.example.newsapp.presentation.navigation.NewsAppNavigation
import com.example.newsapp.presentation.navigation.Screen
import com.example.newsapp.presentation.ui.theme.NewsAppTheme
import com.example.newsapp.presentation.viewModel.SettingsViewModel
import com.example.newsapp.presentation.viewModel.SettingsViewModelFactory
import java.util.Locale


/**
 * App - Корневой компонент приложения.
 * Управляет состоянием языка и основной навигацией.
 */
@Composable
fun App() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val viewModel : SettingsViewModel = viewModel(factory = SettingsViewModelFactory(context))
    val isDarkTheme by viewModel.isDarkTheme.collectAsState()
    val currentLanguage by viewModel.currentLanguage.collectAsState()

    key (currentLanguage,isDarkTheme){
        NewsAppTheme (darkTheme = isDarkTheme){
            MainAppContent(
                navController = navController,
                settingsViewModel = viewModel
            )
        }
    }
    }


    // Пересоздание UI при изменении языка

/**
 * MainAppContent - Основной layout приложения со Scaffold.
 */
@Composable
fun MainAppContent(
    navController: NavHostController,
    settingsViewModel: SettingsViewModel
) {

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopBar(
                currentRoute = currentRoute,
                showBackButton = currentRoute != Screen.NewsScreen.route,
                navController = navController
            )
        },
        bottomBar = { BottomBar(navController, currentRoute) },
        content = { padding ->
            NewsAppNavigation(
                navController = navController,
                settingsViewModel = settingsViewModel,
                modifier = Modifier
                    .padding(padding)
            )
        }
    )
}
