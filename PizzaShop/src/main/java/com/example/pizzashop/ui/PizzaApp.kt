package com.example.pizzashop.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pizzashop.model.Pizza
import com.example.pizzashop.navigation.PizzaNavigation
import com.example.pizzashop.navigation.Screen
import com.example.pizzashop.ui.base_items.BottomBar.PizzaBottomBar
import com.example.pizzashop.ui.base_items.TopAppBar.PizzaTopAppBar
import com.example.pizzashop.ui.base_items.TopAppBar.getScreenTitle

/**
 * Главный компонент приложения
 * Содержит Scaffold с верхним навигационным меню, нижней панелью навигации и контентом
 */
@Composable
fun PizzaApp() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val viewModel: BasketViewModel = viewModel()


    Scaffold(
        topBar = {
            PizzaTopAppBar(
                title = getScreenTitle(currentRoute),
                showBackButton = currentRoute != Screen.Pizza.route,
                onBackClick = { navController.popBackStack() }
            )
        },
        bottomBar = {
            PizzaBottomBar(navController, currentRoute)
        },
        content = { padding ->
            PizzaNavigation(
                navController = navController,
                modifier = Modifier
                    .padding(padding),
                basketList = viewModel.basketItems
            )
        }
    )
}

/**
 * ViewModel для работы с корзиной(сохранение состояния при перевороте)
 * @property basketItems Список пицц в корзине
 */
class BasketViewModel : ViewModel() {
    val basketItems = mutableStateListOf<Pizza>()
}