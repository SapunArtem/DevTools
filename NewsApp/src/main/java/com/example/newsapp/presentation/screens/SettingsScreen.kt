package com.example.newsapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.components.settings.SettingsData
import com.example.newsapp.presentation.components.settings.app_language.LanguageSettingSection
import com.example.newsapp.presentation.components.settings.app_theme.ThemeSettingsSection
import com.example.newsapp.presentation.viewModel.SettingsViewModel


/**
 * Экран настроек приложения.
 *
 * @param viewModel ViewModel для управления настройками
 */
@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val isDarkTheme by viewModel.isDarkTheme.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ThemeSettingsSection(
                items = SettingsData.theme,
                isDarkTheme = isDarkTheme,
                setTheme = { selectedTheme ->
                    viewModel.setTheme(selectedTheme)
                }
            )
        }
        item {
            LanguageSettingSection(
                items = SettingsData.language,
                currentLanguage = currentLanguage,
                setLanguage = { selectedLanguage ->
                    viewModel.setLanguage(selectedLanguage)
                }
            )
        }
    }
}