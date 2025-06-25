package com.example.pizzashop.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.pizzashop.ui.base_items.TopAppBar.ActionGoToBasket
import com.example.pizzashop.ui.base_items.TopAppBar.NavigationIconBack
import com.example.pizzashop.ui.base_items.TopAppBar.PizzaTopAppBar
import com.example.pizzashop.ui.theme.DevToolsTheme


class PizzaShop : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DevToolsTheme {
                Scaffold(
                    topBar = {
                        PizzaTopAppBar(
                            "Корзина",
                            navigationIcon = { NavigationIconBack { } },
                            action = { ActionGoToBasket {  }}
                        )
                    },
                    content = {padding ->
                        Box(
                            modifier = Modifier
                                .padding(padding)
                                .fillMaxSize()
                        ){
                        }
                    }
                )
            }
        }
    }
}

