package com.example.newsapp.data.local.repository

import android.content.Context
import com.example.newsapp.domain.repository.LocalizationRepository
import com.example.newsapp.presentation.components.settings.app_language.LocalizationManager

/**
 * Реализация [LocalizationRepository] для работы с локализацией приложения.
 * Обеспечивает управление языком приложения через [LocalizationManager].
 *
 * @property context Контекст приложения (может быть обновлен через [updateContext])
 * @property localizationManager Менеджер для работы с локализацией
 */
class LocalizationRepositoryImpl(
    private var context: Context,
    private val localizationManager : LocalizationManager
) : LocalizationRepository{
    /**
     * Устанавливает указанный язык как текущий для приложения.
     * @param languageCode Код языка (например, "en", "ru")
     */
    override fun setLanguage(languageCode: String) {
        localizationManager.setLocale(context,languageCode)
    }

    /**
     * Возвращает текущий язык приложения.
     * @return Код текущего языка
     */
    override fun getCurrentLanguage(): String {
        return localizationManager.getCurrentLanguage(context)
    }
    /**
     * Обновляет контекст репозитория.
     * Должен вызываться при изменении конфигурации (например, повороте экрана).
     * @param newContext Новый контекст приложения
     */
    fun updateContext(newContext : Context){
        context = newContext
    }
}