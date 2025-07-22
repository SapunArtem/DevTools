package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.repository.LocalizationRepository

/**
 * UseCase для изменения языка приложения.
 *
 * @property localizationRepository Репозиторий для работы с локализацией
 */
class ChangeAppLanguageUseCase(
    private val localizationRepository: LocalizationRepository
) {
    /**
     * Устанавливает указанный язык как текущий для приложения.
     * @param languageCode Код языка (например, "en", "ru")
     */
    operator fun invoke(languageCode: String) {
        localizationRepository.setLanguage(languageCode)
    }

}