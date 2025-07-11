package com.example.newsapp.presentation.components.top_bar

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.newsapp.R
import com.example.newsapp.presentation.navigation.Screen

/**
 * TopBarTitle - Заголовок верхней панели в зависимости от текущего экрана.
 */
@Composable
fun TopBarTitle(
    currentRoute: String?
) {
    Text(
        text = when (currentRoute) {
            Screen.NewsScreen.route -> stringResource(R.string.news)
            Screen.SettingsScreen.route -> stringResource(R.string.settings)
            Screen.DetailsScreen.route -> stringResource(R.string.details)
            else -> stringResource(R.string.news)
        }
    )
}