package com.example.pizzashop.model

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Представляет элемент нижнего навигационного меню
 * @property label Текст для отображения в навигационном меню
 * @property icon Иконка для элемента в навигационном меню
 * @property route Путь для элемента навигации
 */
data class BottomItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)
