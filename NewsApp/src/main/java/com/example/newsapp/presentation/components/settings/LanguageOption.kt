package com.example.newsapp.presentation.components.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.presentation.ui.theme.AppTheme


@Composable
fun LanguageOption(
    languageCode: String,
    languageName: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onSelect)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val flagIcon = when (languageCode) {
            "en" -> R.drawable.ic_flag_uk
            "ru" -> R.drawable.ic_flag_russia
            else -> R.drawable.ic_flag_unknown
        }

        Image(
            painter = painterResource(id = flagIcon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = languageName,
            style = MaterialTheme.typography.bodyLarge,
            color = if (AppTheme.isDarkTheme) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.weight(1f))

        if (isSelected) {
            androidx.compose.material3.Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = stringResource(R.string.selected),
                tint = MaterialTheme.colorScheme.primary
            )

        }
    }
}