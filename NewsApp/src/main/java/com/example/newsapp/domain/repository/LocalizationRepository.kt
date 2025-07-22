package com.example.newsapp.domain.repository

/**
 * Интерфейс репозитория для работы с локализацией приложения.
 */
interface LocalizationRepository {
    /**
     * Устанавливает язык приложения.
     * @param languageCode Код языка (например, "en", "ru")
     */
    fun setLanguage(languageCode : String)
    /**
     * Возвращает текущий язык приложения.
     * @return Код текущего языка
     */
    fun getCurrentLanguage() : String
}