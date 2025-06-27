package com.example.pizzashop.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pizzashop.model.Pizza
import com.example.pizzashop.navigation.PizzaNavigation
import com.example.pizzashop.navigation.Screen
import com.example.pizzashop.ui.base_items.BottomBar.PizzaBottomBar
import com.example.pizzashop.ui.base_items.TopAppBar.PizzaTopAppBar
import com.example.pizzashop.ui.base_items.TopAppBar.getScreenTitle


@Composable
fun PizzaApp(){
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val basketList = remember { mutableStateListOf<Pizza>() }

    Scaffold (
        topBar = {
            PizzaTopAppBar(
                title = getScreenTitle(currentRoute),
                showBackButton = currentRoute != Screen.Pizza.route,
                onBackClick = {navController.popBackStack()}
            )
        },
        bottomBar = {
            PizzaBottomBar(navController,currentRoute)
        },
        content = {padding->
            PizzaNavigation(
                navController = navController,
                modifier = Modifier
                    .padding(padding),
                basketList = basketList
            )
        }
    )
}