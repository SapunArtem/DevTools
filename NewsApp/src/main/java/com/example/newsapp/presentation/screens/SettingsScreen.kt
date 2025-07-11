package com.example.newsapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.presentation.components.settings.LanguageOption
import com.example.newsapp.presentation.components.settings.ThemeOption
import com.example.newsapp.presentation.ui.theme.AlmostBack
import com.example.newsapp.presentation.ui.theme.AppTheme


/**
 * SettingsScreen - Экран настроек приложения.
 *
 * @param currentLanguage Текущий язык приложения
 * @param setLanguage Функция для изменения языка
 */
@Composable
fun SettingsScreen(
    currentLanguage: String,
    setLanguage: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {

        Text(
            text = stringResource(R.string.app_theme),
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            colors = CardDefaults.cardColors(
                containerColor =
                    if (!AppTheme.isDarkTheme) Color.White else AlmostBack
            ),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    //Светлая тема
                    ThemeOption(
                        imageRes = R.drawable.my_lightfon,
                        title = stringResource(R.string.light_theme),
                        isSelected = !AppTheme.isDarkTheme,
                        onSelect = { AppTheme.isDarkTheme = false }
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                    //Темная тема
                    ThemeOption(
                        imageRes = R.drawable.my_darkfon,
                        title = stringResource(R.string.dark_theme),
                        isSelected = AppTheme.isDarkTheme,
                        onSelect = { AppTheme.isDarkTheme = true }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Настройки языка
        Text(
            text = stringResource(R.string.app_language),
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = if (!AppTheme.isDarkTheme) Color.White else AlmostBack
            ),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column {
                //Английский язык
                LanguageOption(
                    languageCode = "en",
                    languageName = stringResource(R.string.english),
                    isSelected = currentLanguage == "en",
                    onSelect = { setLanguage("en") }
                )

                HorizontalDivider()

                //Русский язык
                LanguageOption(
                    languageCode = "ru",
                    languageName = stringResource(R.string.russian),
                    isSelected = currentLanguage == "ru",
                    onSelect = { setLanguage("ru") }
                )
            }
        }
    }
}