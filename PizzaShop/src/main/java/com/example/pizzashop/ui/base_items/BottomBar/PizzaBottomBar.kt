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
import com.example.pizzashop.repository.BottomNavigatioRepository
import com.example.pizzashop.ui.theme.Orange


@Composable
fun PizzaBottomBar(navController: NavController){
    val selectedNavigationIndex = rememberSaveable { mutableIntStateOf(0) }
    NavigationBar{
        BottomNavigatioRepository.navigationItems.forEachIndexed{ index, bottomItem ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {selectedNavigationIndex.intValue = index
                          navController.navigate(bottomItem.route)},
                icon = {
                    Icon(
                        imageVector = bottomItem.icon,
                        contentDescription = bottomItem.label,
                        tint = if (selectedNavigationIndex.intValue==index){
                            Orange
                        }else{
                            Color.Gray
                        }
                    ) },
                label = {
                    Text(
                        text = bottomItem.label,
                        color = if(selectedNavigationIndex.intValue == index){
                            Orange
                        }else{
                            Color.Gray
                        }
                    ) }
            )
        }
    }
}