package com.example.newsapp.data.local.repository

import com.example.newsapp.domain.repository.ThemeRepository
import com.example.newsapp.presentation.ui.theme.AppTheme

/**
 * Реализация [ThemeRepository] для управления темой приложения.
 * Обеспечивает сохранение и получение текущей темы.
 */
class ThemeRepositoryImpl: ThemeRepository {
    /**
     * Устанавливает текущую тему приложения.
     * @param isDark true для темной темы, false для светлой
     */
    override fun setTheme(isDark: Boolean) {
        AppTheme.isDarkTheme = isDark
    }

    /**
     * Возвращает текущую тему приложения.
     * @return true если выбрана темная тема, false если светлая
     */
    override fun getCurrentTheme(): Boolean {
        return AppTheme.isDarkTheme
    }
}