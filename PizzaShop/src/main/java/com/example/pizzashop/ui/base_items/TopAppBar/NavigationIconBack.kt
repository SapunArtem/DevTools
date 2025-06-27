package com.example.pizzashop.ui.base_items.TopAppBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun NavigationIconBack(
    onBackClick:() -> Unit
){
    IconButton(
        onClick = {onBackClick()}
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Назад",
            tint = Color.White
        )
    }
}