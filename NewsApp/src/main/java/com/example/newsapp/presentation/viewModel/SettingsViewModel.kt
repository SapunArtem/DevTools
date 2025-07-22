package com.example.newsapp.presentation.viewModel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.local.repository.LocalizationRepositoryImpl
import com.example.newsapp.domain.repository.ThemeRepository
import com.example.newsapp.domain.usecase.ChangeAppLanguageUseCase
import com.example.newsapp.domain.usecase.ChangeAppThemeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


/**
 * ViewModel для управления настройками приложения (язык, тема).
 * Предоставляет состояние и методы для изменения настроек.
 *
 * @property changeLanguage UseCase для изменения языка
 * @property localizationRepository Репозиторий локализации (реализация)
 * @property changeTheme UseCase для изменения темы
 * @property themeRepository Репозиторий темы
 */
class SettingsViewModel(
    private val changeLanguage: ChangeAppLanguageUseCase,
    private val localizationRepository: LocalizationRepositoryImpl,
    private val changeTheme : ChangeAppThemeUseCase,
    private val themeRepository: ThemeRepository
) : ViewModel() {

    private val _currentLanguage = MutableStateFlow(localizationRepository.getCurrentLanguage())
    val currentLanguage: StateFlow<String> = _currentLanguage

    private val _isDarkTheme = MutableStateFlow(themeRepository.getCurrentTheme())
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme


    /**
     * Устанавливает выбранный язык приложения.
     *
     * @param languageCode код языка (например, "en", "ru")
     */
    fun setLanguage(languageCode: String) {
        viewModelScope.launch {
            changeLanguage(languageCode)
            _currentLanguage.value = localizationRepository.getCurrentLanguage()
        }
    }
    /**
     * Обновляет контекст при изменении конфигурации.
     * @param newContext Новый контекст
     */
    fun updateContext(newContext : Context){
        localizationRepository.updateContext(newContext)
        _currentLanguage.value = localizationRepository.getCurrentLanguage()
    }

    /**
     * Устанавливает выбранную тему (темная/светлая).
     *
     * @param isDark true - темная тема, false - светлая
     */
    fun setTheme(isDark: Boolean) {
        viewModelScope.launch {
            changeTheme(isDark)
            _isDarkTheme.value = themeRepository.getCurrentTheme()
        }
    }

}