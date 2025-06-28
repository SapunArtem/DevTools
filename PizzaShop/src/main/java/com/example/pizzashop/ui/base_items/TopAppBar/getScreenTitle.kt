package com.example.pizzashop.ui.base_items.TopAppBar

import com.example.pizzashop.navigation.Screen

/**
 * Функция для динамического определения заголовка TopAppBar
 * @param currentRoute текущий путь навигации
 */
fun getScreenTitle(
    currentRoute: String?
): String {
    return when (currentRoute) {
        Screen.Pizza.route -> Screen.Pizza.title
        Screen.Basket.route -> Screen.Basket.title
        Screen.Details.route -> Screen.Details.title
        Screen.MenuMain.route -> Screen.MenuMain.title
        Screen.MenuProfile.route -> Screen.MenuProfile.title
        Screen.MenuSettings.route -> Screen.MenuSettings.title
        Screen.MenuAboutScreen.route -> Screen.MenuAboutScreen.title
        else -> "PizzaShop"
    }
}