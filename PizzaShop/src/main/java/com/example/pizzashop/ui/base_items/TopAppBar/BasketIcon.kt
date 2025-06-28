package com.example.pizzashop.ui.base_items.TopAppBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Иконка корзины
 */
@Composable
fun BasketIcon() {
    Icon(
        imageVector = Icons.Default.ShoppingCart,
        contentDescription = "Корзина",
        tint = Color.White
    )
}