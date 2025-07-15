package com.example.newsapp.presentation.viewModel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecase.ChangeAppLanguageUseCase
import com.example.newsapp.domain.usecase.ChangeAppThemeUseCase
import com.example.newsapp.presentation.components.settings.LocalizationManager
import com.example.newsapp.presentation.ui.theme.AppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val context: Context
): ViewModel() {
    private val changeLanguage = ChangeAppLanguageUseCase(context)
    private val changeTheme = ChangeAppThemeUseCase()

    private val _currentLanguage = MutableStateFlow(LocalizationManager.getCurrentLanguage(context))
    val  currentLanguage : StateFlow<String> = _currentLanguage

    private val _isDarkTheme = MutableStateFlow(AppTheme.isDarkTheme)
    val isDarkTheme : StateFlow<Boolean> = _isDarkTheme


    fun setLanguage(languageCode : String){
        viewModelScope.launch {
            changeLanguage(languageCode)
            _currentLanguage.value = languageCode
        }
    }

    fun setTheme(isDark: Boolean){
        viewModelScope.launch {
            changeTheme(isDark)
            _isDarkTheme.value = isDark
        }
    }
}