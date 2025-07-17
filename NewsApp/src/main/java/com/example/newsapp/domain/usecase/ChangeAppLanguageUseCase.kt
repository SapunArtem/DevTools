package com.example.newsapp.domain.usecase

import android.content.Context
import com.example.newsapp.presentation.components.settings.app_language.LocalizationManager

class ChangeAppLanguageUseCase(
    private val context: Context
) {
    operator fun invoke(languageCode : String){
        LocalizationManager.setLocale(context,languageCode)
    }
}