package com.example.newsapp.presentation.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecase.ChangeAppLanguageUseCase
import com.example.newsapp.domain.usecase.ChangeAppThemeUseCase
import com.example.newsapp.presentation.components.settings.app_language.LocalizationManager
import com.example.newsapp.presentation.ui.theme.AppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


/**
 * ViewModel для управления настройками приложения (язык, тема).
 *
 * @param context контекст для доступа к ресурсам и локализации
 */
class SettingsViewModel(
    private val context: Context
) : ViewModel() {
    private val changeLanguage = ChangeAppLanguageUseCase(context)
    private val changeTheme = ChangeAppThemeUseCase()

    private val _currentLanguage = MutableStateFlow(LocalizationManager.getCurrentLanguage(context))
    val currentLanguage: StateFlow<String> = _currentLanguage

    private val _isDarkTheme = MutableStateFlow(AppTheme.isDarkTheme)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme


    /**
     * Устанавливает выбранный язык приложения.
     *
     * @param languageCode код языка (например, "en", "ru")
     */
    fun setLanguage(languageCode: String) {
        viewModelScope.launch {
            changeLanguage(languageCode)
            _currentLanguage.value = languageCode
        }
    }

    /**
     * Устанавливает выбранную тему (темная/светлая).
     *
     * @param isDark true - темная тема, false - светлая
     */
    fun setTheme(isDark: Boolean) {
        viewModelScope.launch {
            changeTheme(isDark)
            _isDarkTheme.value = isDark
        }
    }

    /**
     * Обновляет контекст ViewModel при изменении конфигурации.
     *
     * @param newContext Новый контекст после изменения конфигурации
     */
    fun updateContext(newContext: Context){
        changeLanguage.updateContext(newContext)
        updateLanguage()
    }
    /**
     * Обновляет текущий язык из SharedPreferences.
     */
    private fun updateLanguage(){
        _currentLanguage.value = LocalizationManager.getCurrentLanguage(context = context)
    }
}