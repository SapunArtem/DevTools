package com.example.pizzashop.ui.base_items.TopAppBar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pizzashop.ui.theme.BasketAppBar
import com.example.pizzashop.ui.theme.Orange


@Composable
fun ActionGoToBasket(
    onBasketClick: () -> Unit
) {
    Button(
        onClick = { onBasketClick() },
        colors = ButtonDefaults.buttonColors(Orange)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Корзина",
                style = BasketAppBar,
                modifier = Modifier
                    .padding(end = 5.dp)
            )
            BasketIcon()

        }
    }
}