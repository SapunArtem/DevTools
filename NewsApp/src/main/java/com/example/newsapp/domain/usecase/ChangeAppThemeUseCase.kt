package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.repository.ThemeRepository


/**
 * UseCase для изменения темы приложения.
 *
 * @property themeRepository Репозиторий для работы с темой
 */
class ChangeAppThemeUseCase(
    private val themeRepository: ThemeRepository
) {
    /**
     * Устанавливает тему приложения.
     *
     * @param isDark true — тёмная тема, false — светлая тема.
     */
    operator fun invoke(isDark: Boolean) {
        themeRepository.setTheme(isDark)
    }
}