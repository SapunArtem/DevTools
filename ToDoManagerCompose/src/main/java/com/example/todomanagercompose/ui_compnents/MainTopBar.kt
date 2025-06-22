package com.example.todomanagercompose.ui_compnents

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.todomanagercompose.ui.theme.Purple80

/**
 * функция для создания кастомного TopAppBar для ToDoManager
 *
 * Реализован с использованием Material3 TopAppBar с фиксированными параметрами:
 * - Фиолетовый фон(Purple 80)
 * - Белый текст с жирным начертанием
 * - Без кнопок навигаци/действия
 *
 * @sample com.example.todomanagercompose.ui.preview.MainTopAppBarPreview
 * @see androidx.compose.material3.TopAppBar
 * @see androidx.compose.material3.MaterialTheme
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar() {
    TopAppBar(
        title = {
            Text(
                "ToDoManager",
                fontWeight = FontWeight.Bold)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Purple80,
            titleContentColor = Color.White
        )
    )
}



