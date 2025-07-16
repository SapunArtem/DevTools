package com.example.newsapp.presentation.components.settings

/**
 * Абстрактный тип для элементов настроек приложения.
 * Позволяет создавать и группировать разные типы настроек в одном списке.
 */
sealed class SettingsItem {

    /**
     * Настройка темы (светлая/тёмная).
     * @param titleRes - ресурс заголовка
     * @param isDarkTheme - флаг темной темы
     * @param imageRes - изображение темы
     */
    data class ThemeSettings(
        val titleRes: Int,
        val isDarkTheme: Boolean,
        val imageRes: Int
    ) : SettingsItem()

    /**
     * Настройка языка.
     * @param titleRes - заголовок языка
     * @param languageCode - код языка (например, "en", "ru")
     * @param flagRes - ресурс изображения флага
     */
    data class LanguageSetting(
        val titleRes: Int,
        val languageCode: String,
        val flagRes: Int
    ) : SettingsItem()

    /**
     * Заголовок секции настроек.
     * @param titleRes - ресурс заголовка
     */
    data class Header(val titleRes: Int) : SettingsItem()
}