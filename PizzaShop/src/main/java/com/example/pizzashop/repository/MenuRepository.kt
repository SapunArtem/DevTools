package com.example.pizzashop.repository

import com.example.pizzashop.model.MenuItem
import com.example.pizzashop.navigation.Screen

object MenuRepository {
    val menuItems = listOf(
        MenuItem(
            "Профиль",
            Screen.Profile.route
        ),
        MenuItem(
            "Настройки",
            Screen.Settings.route
        ),
        MenuItem(
            "О приложении",
            Screen.AboutScreen.route
        )
    )
}