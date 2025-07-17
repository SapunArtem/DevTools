package com.example.newsapp.domain.usecase

import com.example.newsapp.presentation.ui.theme.AppTheme


/**
 * UseCase для изменения темы приложения.
 */
class ChangeAppThemeUseCase {
    /**
     * Устанавливает тему приложения.
     *
     * @param isDarkTheme true — тёмная тема, false — светлая тема.
     */
    operator fun invoke(isDarkTheme: Boolean) {
        AppTheme.isDarkTheme = isDarkTheme
    }
}