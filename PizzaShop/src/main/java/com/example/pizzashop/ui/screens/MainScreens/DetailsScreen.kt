package com.example.pizzashop.ui.screens.MainScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizzashop.model.Pizza
import com.example.pizzashop.ui.theme.Orange
import com.example.pizzashop.ui.theme.PizzaDescription
import com.example.pizzashop.ui.theme.PizzaName
import com.example.pizzashop.ui.theme.PizzaPrice
import com.example.pizzashop.ui.theme.TextDescription

/**
 * Экран делей пиццы
 * @param pizza Данные о пицце
 * @param onAddToBasketClick Обработчик добавления в корзину
 */
@Composable
fun DetailsScreen(
    pizza: Pizza,
    onAddToBasketClick: () -> Unit,

    ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(pizza.image),
                contentDescription = pizza.name,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = pizza.name,
                modifier = Modifier.padding(start = 15.dp),
                style = PizzaName
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Описание",
                modifier = Modifier.padding(start = 15.dp),
                style = TextDescription
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = pizza.description,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(start = 15.dp),
                style = PizzaDescription
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.padding(start = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier
                        .testTag("add_btn"),
                    onClick = { onAddToBasketClick() },
                    colors = ButtonDefaults.buttonColors(Orange)
                ) {
                    Text(
                        text = "В корзину",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Text(
                    text = pizza.price.toString(),
                    modifier = Modifier.padding(start = 15.dp),
                    style = PizzaPrice
                )
            }

        }
    }
}
