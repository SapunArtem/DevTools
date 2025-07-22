package com.example.pizzashop.ui.Cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pizzashop.model.Pizza
import com.example.pizzashop.ui.theme.Orange
import com.example.pizzashop.ui.theme.PizzaName
import com.example.pizzashop.ui.theme.PizzaPrice

/**
 * Элемент списка пицц добавленных в корзину в виде карточки
 * @param pizza Данные о пицце
 * @param onRemoveClick Обработчик удаления пиццы из корзины
 */
@Composable
fun BasketCard(
    pizza: Pizza,
    onRemoveClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)

            ) {
                Image(
                    painter = painterResource(pizza.image),
                    contentDescription = pizza.name,
                    modifier = Modifier
                        .size(80.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = pizza.name,
                    style = PizzaName
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = pizza.price.toString(),
                    style = PizzaPrice
                )
            }
            IconButton(
                modifier = Modifier
                    .testTag("удалить"),
                onClick = { onRemoveClick() }
            ) {
                Icon(
                    Icons.Default.Delete,
                    "Удалить",
                    tint = Orange
                )
            }
        }
    }
}