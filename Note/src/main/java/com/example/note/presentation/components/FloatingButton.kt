package com.example.note.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.note.presentation.ui.theme.Orange

@Composable
fun FloatingButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(bottom = 60.dp, end = 20.dp)
    ) {
        FloatingActionButton(
            onClick = { onClick() },
            modifier = Modifier
                .size(70.dp),
            shape = CircleShape,
            containerColor = Orange,
            contentColor = Color.White,
            elevation = FloatingActionButtonDefaults.elevation(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Добавить задачу",
                modifier = Modifier
                    .size(40.dp)
            )
        }
    }
}