package com.example.newsapp.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.components.bottom_nav.BottomBar
import com.example.newsapp.presentation.components.settings.LocalizationManager
import com.example.newsapp.presentation.components.top_bar.TopBar
import com.example.newsapp.presentation.navigation.NewsAppNavigation
import com.example.newsapp.presentation.navigation.Screen
import java.util.Locale


/**
 * App - Корневой компонент приложения.
 * Управляет состоянием языка и основной навигацией.
 */
@Composable
fun App() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val view = LocalView.current
    var currentLanguage by remember {
        mutableStateOf(LocalizationManager.getCurrentLanguage(context))
    }

    // Функция изменения языка
    val setLanguage: (String) -> Unit = { lang ->
        LocalizationManager.setLocale(context, lang)
        currentLanguage = lang
        view.post {
            val config = Configuration(context.resources.configuration)
            config.setLocale(Locale(lang))
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
        }
    }

    // Пересоздание UI при изменении языка
    key(currentLanguage) {
        MainAppContent(
            navController = navController,
            currentLanguage = currentLanguage,
            setLanguage = setLanguage
        )
    }
}

/**
 * MainAppContent - Основной layout приложения со Scaffold.
 */
@Composable
fun MainAppContent(
    navController: NavHostController,
    currentLanguage: String,
    setLanguage: (String) -> Unit
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
                currentLanguage = currentLanguage,
                setLanguage = setLanguage,
                modifier = Modifier
                    .padding(padding)
            )
        }
    )
}
