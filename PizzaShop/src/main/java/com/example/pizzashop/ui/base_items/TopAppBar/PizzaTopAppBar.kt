package com.example.pizzashop.ui.base_items.TopAppBar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.example.pizzashop.ui.theme.Orange
import com.example.pizzashop.ui.theme.TitleAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaTopAppBar(
    title : String,
    action: @Composable RowScope.() -> Unit = {},
    showBackButton : Boolean = false,
    onBackClick:()->Unit
){
    TopAppBar(
        title = {
            Text(
                text = title,
                style = TitleAppBar
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Orange
        ),

        navigationIcon = {
            if (showBackButton){
            NavigationIconBack { onBackClick() }
        } },
        actions = action

    )
}