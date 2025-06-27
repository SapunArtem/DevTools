package com.example.pizzashop.ui.screens.MenuScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pizzashop.R
import com.example.pizzashop.navigation.Screen
import com.example.pizzashop.ui.theme.PizzaName
import com.example.pizzashop.ui.theme.TextDescription


@Composable
fun ProfileScreen(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .clip(CircleShape)

        ){
            Image(
                painter = painterResource(R.drawable.ranch),
                contentDescription = "Профиль",
                modifier = Modifier
                    .size(150.dp),
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.width(200.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text("Имя:", style = TextDescription)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text("Фамилия:", style = TextDescription)
                }

                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text("Артём", style = PizzaName)
                    Spacer(modifier = Modifier.height(15.dp))
                    Text("Сапун", style = PizzaName)
                }
            }
        }
    }





