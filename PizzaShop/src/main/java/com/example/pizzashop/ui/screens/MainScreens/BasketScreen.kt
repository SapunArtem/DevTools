package com.example.pizzashop.ui.screens.MainScreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pizzashop.model.Pizza
import com.example.pizzashop.ui.Cards.BasketCard
import com.example.pizzashop.ui.theme.Orange

/**
 * Экран корзины с пиццами
 * @param pizzas Список пицц в корзине
 * @param onRemoveClick Обработчик удаления пиццы из корзины
 */
@Composable
fun BasketScreen(
    pizzas: List<Pizza>,
    onRemoveClick: (Pizza) -> Unit
) {

    if (pizzas.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = "Корзина пуста",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Orange
            )
        }
    } else {
        LazyColumn()
        {
            items(pizzas) { pizza ->
                BasketCard(
                    pizza = pizza,
                    onRemoveClick = { onRemoveClick(pizza) }
                )
            }
        }
    }
}

