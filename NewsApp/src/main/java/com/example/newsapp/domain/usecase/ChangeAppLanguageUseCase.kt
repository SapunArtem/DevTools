package com.example.newsapp.domain.usecase

import android.content.Context
import com.example.newsapp.presentation.components.settings.LocalizationManager

class ChangeAppLanguageUseCase(
    private val context: Context
) {
    operator fun invoke(languageCode : String){
        LocalizationManager.setLocale(context,languageCode)
    }
}