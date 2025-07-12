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
            title = "Профиль",
            route = Screen.MenuProfile.route
        ),
        MenuItem(
            title = "Настройки",
            route = Screen.MenuSettings.route
        ),
        MenuItem(
            title = "О приложении",
            route = Screen.MenuAbout.route
        )
    )
}