package com.example.pizzashop.ui.Cards

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import com.example.pizzashop.model.MenuItem
import com.example.pizzashop.ui.theme.TextDescription


/**
 * Элемент для отображения списка пунктов меню в виде карточки
 * @param menuItem Элемент списка пунктов меню
 * @param onMenuCadClick Обработчик нажатия на пункт меню
 */
@Composable
fun MenuCard(
    menuItem: MenuItem,
    onMenuCadClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .height(60.dp)
            .fillMaxWidth()
            .clickable { onMenuCadClick() },
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxHeight()
                .wrapContentSize(Alignment.Center),
            text = menuItem.title,
            style = TextDescription
        )


    }
}