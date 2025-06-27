package com.example.pizzashop.ui.screens.MainScreens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.pizzashop.model.Pizza
import com.example.pizzashop.repository.PizzaRepository
import com.example.pizzashop.ui.Cards.PizzaCard

@Composable
fun PizzaScreen(
    onPizzaClick:(Int)->Unit,
    onAddToBasket:(Pizza)->Unit,
){

            val pizzas = remember { PizzaRepository.getAllPizzas() }
            LazyColumn { items(pizzas){pizza->
                PizzaCard(
                    pizza = pizza,
                    onPizzaClick = {onPizzaClick(pizza.id)},
                    onAddToBasket = {onAddToBasket(pizza)}
                )
            } }
        }

