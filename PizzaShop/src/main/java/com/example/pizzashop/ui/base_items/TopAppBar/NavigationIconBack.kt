package com.example.pizzashop.ui.base_items.TopAppBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag

/**
 * Кнопка навигации(кнопка назад) в TopAppBar
 * @param onBackClick Обработчик нажатия кнопки назад
 */
@Composable
fun NavigationIconBack(
    onBackClick:() -> Unit
){
    IconButton(
        modifier = Modifier
            .testTag("назад"),
        onClick = {onBackClick()}
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Назад",
            tint = Color.White
        )
    }
}