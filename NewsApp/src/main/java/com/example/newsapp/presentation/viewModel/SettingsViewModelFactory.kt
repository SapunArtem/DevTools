package com.example.newsapp.presentation.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.data.local.repository.LocalizationRepositoryImpl
import com.example.newsapp.data.local.repository.ThemeRepositoryImpl
import com.example.newsapp.domain.usecase.ChangeAppLanguageUseCase
import com.example.newsapp.domain.usecase.ChangeAppThemeUseCase
import com.example.newsapp.presentation.components.settings.app_language.LocalizationManager


/**
 * Factory для создания SettingsViewModel с передачей контекста.
 *
 * @param context контекст приложения
 */
class SettingsViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            val localizationManager = LocalizationManager
            val localizationRepository = LocalizationRepositoryImpl(context,localizationManager)
            val themeRepository = ThemeRepositoryImpl()
            return SettingsViewModel(
                changeLanguage = ChangeAppLanguageUseCase(localizationRepository),
                changeTheme = ChangeAppThemeUseCase(themeRepository),
                localizationRepository = localizationRepository,
                themeRepository = themeRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
