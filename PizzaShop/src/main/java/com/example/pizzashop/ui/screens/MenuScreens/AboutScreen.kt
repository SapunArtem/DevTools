package com.example.pizzashop.ui.screens.MenuScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pizzashop.ui.theme.PizzaDescription
import com.example.pizzashop.ui.theme.TextDescription

/**
 * Экран описывающий приложение
 */
@Composable
fun AboutScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Приложение для заказа пиццы",
                style = TextDescription
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Название : PizzaShop",
                style = TextDescription
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Возможности приложения",
                style = TextDescription
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "- Добавление пиццы в корзину\n" +
                        "- Просмотр информации о пицце\n" +
                        "- Удаление пиццы из корзины\n" +
                        "- Просмотр профиля\n" +
                        "- Настройки",
                style = PizzaDescription
            )

        }
    }
}