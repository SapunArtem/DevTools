package com.example.pizzashop.repository

import com.example.pizzashop.model.MenuItem
import com.example.pizzashop.navigation.Screen


/**
 * Репозиторий для пунктов меню экрана Menu
 * @property menuItems Список пунктов меню
 */
object MenuRepository {
    val menuItems = listOf(
        MenuItem(
            "Профиль",
            Screen.MenuProfile.route
        ),
        MenuItem(
            "Настройки",
            Screen.MenuSettings.route
        ),
        MenuItem(
            "О приложении",
            Screen.MenuAboutScreen.route
        )
    )
}