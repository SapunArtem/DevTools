package com.example.pizzashop.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pizzashop.model.Pizza
import com.example.pizzashop.ui.Cards.BasketCard
import com.example.pizzashop.ui.base_items.TopAppBar.ActionGoToBasket
import com.example.pizzashop.ui.base_items.TopAppBar.NavigationIconBack
import com.example.pizzashop.ui.base_items.TopAppBar.PizzaTopAppBar
import com.example.pizzashop.ui.theme.Orange

@Composable
fun BasketScreen(
    pizzas : List<Pizza>,
    onRemoveClick:()->Unit
){
    Scaffold (
        topBar = {
            PizzaTopAppBar(
                "Корзина",
                navigationIcon = { NavigationIconBack {  }},
                action = { ActionGoToBasket {  }}
            )
        },
        content = {padding->
            if (pizzas.isEmpty()) {
                Box(
                    modifier = Modifier
                        .padding(padding)
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
            }else{
                LazyColumn(
                    modifier = Modifier
                        .padding(padding)
                )
                { items(pizzas) {pizza->
                    BasketCard(
                        pizza = pizza,
                        onRemoveClick = {onRemoveClick()}
                    )
                } }
            }
        }
    )
}