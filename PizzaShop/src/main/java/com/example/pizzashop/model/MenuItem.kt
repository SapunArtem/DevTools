package com.example.pizzashop.model

/**
 * Представляет элемент экрана Menu
 * @property title Название элемента меню
 * @property route Путь для навигации
 */
data class MenuItem(
    val title: String,
    val route: String
)