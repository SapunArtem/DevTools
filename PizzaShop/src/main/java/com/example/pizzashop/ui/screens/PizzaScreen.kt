package com.example.pizzashop.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.pizzashop.repository.PizzaRepository
import com.example.pizzashop.ui.Cards.PizzaCard
import com.example.pizzashop.ui.base_items.TopAppBar.ActionGoToBasket
import com.example.pizzashop.ui.base_items.TopAppBar.PizzaTopAppBar

@Composable
fun PizzaScreen(
    onPizzaClick:(Int)->Unit,
    onAddToBasket:()->Unit
){
    Scaffold(
        topBar = {
            PizzaTopAppBar(
                "Пицца",
                action = { ActionGoToBasket { } }
            )
        },
        content = {padding->
            val pizzas = remember { PizzaRepository.getAllPizzas() }
            LazyColumn (
                modifier = Modifier
                    .padding(padding)
            ){ items(pizzas){pizza->
                PizzaCard(
                    pizza = pizza,
                    onPizzaClick = {onPizzaClick(pizza.id)},
                    onAddToBasket = {onAddToBasket()}
                )
            } }
        }
    )
}