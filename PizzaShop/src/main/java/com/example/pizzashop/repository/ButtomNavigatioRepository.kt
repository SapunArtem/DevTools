package com.example.pizzashop.repository

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import com.example.pizzashop.model.BottomItem
import com.example.pizzashop.navigation.Screen


/**
 * Репозиторий для элементов нижнего навигационного меню
 * @property navigationItems Список элементов навигации
 */
object BottomNavigatioRepository {
    val navigationItems = listOf(
        BottomItem(
            label = "Pizza",
            icon = Icons.Filled.Home,
            route = Screen.Pizza.route
        ),
        BottomItem(
            "Basket",
            Icons.Filled.ShoppingCart,
            route = Screen.Basket.route
        ),
        BottomItem(
            "Menu",
            Icons.Filled.Menu,
            route = "menu_root"
        ),

        )
}