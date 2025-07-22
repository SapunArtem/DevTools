package com.example.newsapp.domain.repository

/**
 * Интерфейс репозитория для работы с темой приложения.
 */
interface ThemeRepository {
    /**
     * Устанавливает тему приложения.
     * @param isDark true для темной темы, false для светлой
     */
    fun setTheme(isDark: Boolean)

    /**
     * Возвращает текущую тему приложения.
     * @return true если выбрана темная тема, false если светлая
     */
    fun getCurrentTheme(): Boolean
}