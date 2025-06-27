package com.example.pizzashop.ui.screens.MainScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pizzashop.repository.MenuRepository
import com.example.pizzashop.ui.Cards.MenuCard


@Composable
fun MenuScreen(
    navController: NavController
){

        LazyColumn {
            items(MenuRepository.menuItems){menuItem->
                Spacer(modifier = Modifier.height(10.dp))
                MenuCard(
                    menuItem = menuItem,
                    onMenuCadClick = {navController.navigate(menuItem.route){
                        launchSingleTop = true
                    } }
                )
        }

        }
    }
