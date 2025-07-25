package com.example.pizzashop.ui.Cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pizzashop.model.Pizza
import com.example.pizzashop.ui.base_items.TopAppBar.BasketIcon
import com.example.pizzashop.ui.theme.Orange
import com.example.pizzashop.ui.theme.PizzaName
import com.example.pizzashop.ui.theme.PizzaPrice


/**
 * Элемент списка пицц в виде карточки
 * @param pizza Информация о пицце
 * @param onPizzaClick Обработчие нажатия на карточки пиццы
 * @param onAddToBasket Обработчик добавления пиццы в корзину
 */
@Composable
fun PizzaCard(
    pizza: Pizza,
    onPizzaClick: () -> Unit,
    onAddToBasket: () -> Unit
) {
    Card(
        modifier = Modifier
            .testTag("pizza_${pizza.id}")
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .clickable(onClick = { onPizzaClick() }
            ),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(pizza.image),
                contentDescription = pizza.name,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier
                    .padding(start = 15.dp, bottom = 6.dp),
                text = pizza.name,
                style = PizzaName
            )
            Text(
                modifier = Modifier
                    .padding(start = 25.dp),
                text = pizza.price.toString(),
                style = PizzaPrice
            )
            Button(
                onClick = { onAddToBasket() },
                modifier = Modifier
                    .padding(start = 15.dp, bottom = 5.dp)
                    .testTag("add_btn_${pizza.id}"),
                colors = ButtonDefaults.buttonColors(Orange),
                elevation = ButtonDefaults.buttonElevation(4.dp)
            ) {
                BasketIcon()
            }
        }
    }
}