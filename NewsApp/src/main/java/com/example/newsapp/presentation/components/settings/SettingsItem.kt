package com.example.newsapp.presentation.components.settings

/**
 * Класс для описания элементов настроек.
 */
sealed class SettingsItem {
    /**
     * Элемент настроек, отвечающий за выбор темы.
     *
     * @property titleRes Ресурс заголовка.
     * @property isDarkTheme Флаг, обозначающий темную тему.
     * @property imageRes Ресурс изображения темы.
     */
    data class ThemeSettings(
        val titleRes: Int,
        val isDarkTheme: Boolean,
        val imageRes: Int
    ) : SettingsItem()

    /**
     * Элемент настроек для выбора языка.
     *
     * @property titleRes Ресурс заголовка языка.
     * @property languageCode Код языка (например, "en").
     * @property flagRes Ресурс иконки флага.
     */
    data class LanguageSettings(
        val titleRes: Int,
        val languageCode: String,
        val flagRes: Int
    ) : SettingsItem()

    /**
     * Заголовок секции настроек.
     *
     * @property titleRes Ресурс заголовка.
     */
    data class Header(val titleRes: Int) : SettingsItem()
}