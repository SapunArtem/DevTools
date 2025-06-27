package com.example.pizzashop.ui.base_items.BottomBar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.pizzashop.navigation.Screen
import com.example.pizzashop.repository.BottomNavigatioRepository
import com.example.pizzashop.ui.theme.Orange


@Composable
fun PizzaBottomBar(
    navController: NavController,
    currentRoute : String?
){

    NavigationBar {
            BottomNavigatioRepository.navigationItems.forEach { bottomItem ->
                val isSelected = when {
                    bottomItem.route == Screen.MenuMain.route -> currentRoute?.startsWith("menu_") == true
                    else -> currentRoute == bottomItem.route
                }
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(bottomItem.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                        Icon(
                            imageVector = bottomItem.icon,
                            contentDescription = bottomItem.label,
                            tint = if (isSelected){
                                Orange
                            }else{
                                Color.Gray
                            }
                        )
                    },
                label = {
                    Text(
                        text = bottomItem.label,
                        color = if (isSelected){
                            Orange
                        }else{
                            Color.Gray
                        }
                    )
                }
            )
        }
    }
    }

