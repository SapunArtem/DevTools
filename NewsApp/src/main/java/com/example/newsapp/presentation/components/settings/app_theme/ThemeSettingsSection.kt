package com.example.newsapp.presentation.components.settings.app_theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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

/**
 * Секция настроек темы с заголовком и выбором между светлой и темной темами.
 *
 * @param items Список доступных тем.
 * @param isDarkTheme Текущая выбранная тема (true — тёмная).
 * @param setTheme Функция для изменения темы.
 */
@Composable
fun ThemeSettingsSection(
    items: List<SettingsItem.ThemeSettings>,
    isDarkTheme: Boolean,
    setTheme: (Boolean) -> Unit


) {

    Text(
        text = stringResource(SettingsData.themeHeader.titleRes),
        color = MaterialTheme.colorScheme.secondary
    )
    Spacer(modifier = Modifier.height(8.dp))

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (!isDarkTheme) Color.White else AlmostBack
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items.forEach { item ->
                ThemeOption(
                    item = item,
                    isSelected = isDarkTheme == item.isDarkTheme,
                    onSelect = { setTheme(item.isDarkTheme) }
                )
            }
        }
    }
}