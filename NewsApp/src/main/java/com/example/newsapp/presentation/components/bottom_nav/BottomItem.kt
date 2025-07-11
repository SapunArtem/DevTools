package com.example.newsapp.presentation.components.bottom_nav

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * BottomItem - Модель данных для элемента нижней навигации.
 *
 * @param title Ресурс строки с названием элемента
 * @param icon Иконка элемента (ImageVector)
 * @param route Маршрут для навигации
 */
data class BottomItem(
    val title: Int,
    val icon: ImageVector,
    val route: String
)
