package com.example.pizzashop.ui.base_items.TopAppBar

import com.example.pizzashop.navigation.Screen

fun getScreenTitle(
    currentRoute: String?
) : String {
    return when(currentRoute){
        Screen.Pizza.route -> Screen.Pizza.title
        Screen.Basket.route -> Screen.Basket.title
        Screen.Details.route -> Screen.Details.title
        Screen.Menu.route -> Screen.Menu.title
        Screen.Profile.route -> Screen.Profile.title
        Screen.Settings.route -> Screen.Settings.title
        else -> "PizzaShop"
    }
}