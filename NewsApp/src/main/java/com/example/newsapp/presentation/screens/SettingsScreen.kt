package com.example.newsapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.components.settings.app_language.LanguageSettingSection
import com.example.newsapp.presentation.components.settings.SettingsData
import com.example.newsapp.presentation.components.settings.app_theme.ThemeSettingsSection


/**
 * SettingsScreen - Экран настроек приложения.
 *
 * @param currentLanguage Текущий язык приложения
 * @param setLanguage Функция для изменения языка
 */
@Composable
fun SettingsScreen(
    currentLanguage: String,
    setLanguage: (String) -> Unit,
) {


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ThemeSettingsSection(
                items = SettingsData.theme
            )
        }
        item {
            LanguageSettingSection(
                items = SettingsData.language,
                currentLanguage = currentLanguage,
                setLanguage = setLanguage
            )
        }
    }

}