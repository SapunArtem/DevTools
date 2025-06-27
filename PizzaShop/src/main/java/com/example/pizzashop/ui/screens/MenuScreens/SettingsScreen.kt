package com.example.pizzashop.ui.screens.MenuScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pizzashop.ui.theme.TextDescription

@Preview
@Composable
fun SettingsScreen(){
    Column(
        modifier = Modifier
            .padding(top = 20.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(5.dp)
                .height(60.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ){
            Text(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxHeight()
                    .wrapContentSize(Alignment.Center),
                text = "Тема приложения",
                style = TextDescription
            )

        }
        Card(
            modifier = Modifier
                .padding(5.dp)
                .height(60.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ){
            Text(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxHeight()
                    .wrapContentSize(Alignment.Center),
                text = "Карты",
                style = TextDescription
            )

        }
        Card(
            modifier = Modifier
                .padding(5.dp)
                .height(60.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ){
            Text(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxHeight()
                    .wrapContentSize(Alignment.Center),
                text = "Заказы",
                style = TextDescription
            )

        }
        Card(
            modifier = Modifier
                .padding(5.dp)
                .height(60.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ){
            Text(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxHeight()
                    .wrapContentSize(Alignment.Center),
                text = "Отображение",
                style = TextDescription
            )

        }
    }

}