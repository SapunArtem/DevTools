package com.example.newsapp.presentation.components.settings.app_language

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import com.example.newsapp.presentation.components.settings.SettingsData
import com.example.newsapp.presentation.components.settings.SettingsItem
import com.example.newsapp.presentation.ui.theme.AlmostBack
import com.example.newsapp.presentation.ui.theme.AppTheme

/**
 * Секция выбора языка с заголовком, списком языков и обработкой выбора.
 *
 * @param items Список доступных языков.
 * @param currentLanguage Код текущего выбранного языка.
 * @param setLanguage Функция для установки выбранного языка.
 */
@Composable
fun LanguageSettingSection(
    items: List<SettingsItem.LanguageSettings>,
    currentLanguage: String,
    setLanguage: (String) -> Unit,
) {

    Text(
        text = stringResource(SettingsData.languageHeader.titleRes),
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
            items.forEach { item ->
                LanguageOption(
                    item = item,
                    isSelected = currentLanguage == item.languageCode,
                    onSelect = { setLanguage(item.languageCode) }
                )
                if (item != items.last()) {
                    HorizontalDivider()
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}