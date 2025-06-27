package com.example.pizzashop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.pizzashop.navigation.PizzaNavigation
import com.example.pizzashop.ui.PizzaApp
import com.example.pizzashop.ui.theme.DevToolsTheme


class PizzaShop : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DevToolsTheme {
               Surface (
                   modifier = Modifier
                       .fillMaxSize()
               ){
                   PizzaApp()
               }
            }
        }
    }
}

