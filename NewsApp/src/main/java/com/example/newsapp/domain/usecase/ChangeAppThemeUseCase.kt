package com.example.newsapp.domain.usecase

import com.example.newsapp.presentation.ui.theme.AppTheme

class ChangeAppThemeUseCase {
    operator fun invoke(isDarkTheme : Boolean){
        AppTheme.isDarkTheme = isDarkTheme
    }
}