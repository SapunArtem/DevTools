package com.example.newsapp.domain.usecase

import android.content.Context
import com.example.newsapp.presentation.components.settings.app_language.LocalizationManager

/**
 * UseCase для изменения языка приложения.
 *
 * @property context Контекст приложения.
 */
class ChangeAppLanguageUseCase(
    private val context: Context
) {
    /**
     * Устанавливает выбранный язык в приложении.
     *
     * @param languageCode Код языка (например, "en", "ru").
     */
    operator fun invoke(languageCode: String) {
        LocalizationManager.setLocale(context, languageCode)
    }
}