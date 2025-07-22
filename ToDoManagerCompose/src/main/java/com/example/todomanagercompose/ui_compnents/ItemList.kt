package com.example.todomanagercompose.ui_compnents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todomanagercompose.model.Task
import com.example.todomanagercompose.ui.theme.MainContainer
import com.example.todomanagercompose.ui.theme.OrangeForAddButton

/**
 * Функция описывает элемент списка задач
 *
 * Отображает карточку задачи с:
 * - Чекбоксом для отметки выполнения
 * - Названием, описанием и датой создания
 * - Кнопкой удаления задачи
 *
 * Визуальное поведение
 * - Выполненные задачи отображаются с серым текстом и полупрозрачным фоном
 * - Невыполненные - с черным текстом на белом фоне
 *
 * Требования
 * - Рекомендуется использовать в LazyColumn для списков
 *
 * @param task Модель данных задачи для отображения
 * @param onDelete Лямбда функция, вызываемая при удалении задачи
 * @param onCheckedChange Лямбда функция, вызываемая при изменении состояния чекбокса
 *
 * @sample com.example.todomanagercompose.preview.TaskItemPreview
 * @see androidx.compose.material3.Card
 * @see androidx.compose.material3.Checkbox
 *
 */
@Composable
fun ItemList(
    task: Task,
    onDelete: () -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    val backgroundColor =
        if (task.isCompleted) MainContainer else Color.White

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .background(backgroundColor)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(checkedColor = OrangeForAddButton)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = if (task.isCompleted) Color.Gray else Color.Black
                )
                if (task.description.isNotBlank()) {
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (task.isCompleted) Color.LightGray else Color.DarkGray
                    )
                }
                Text(
                    text = task.createdAt,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Удалить",
                    tint = OrangeForAddButton
                )
            }
        }
    }
}
