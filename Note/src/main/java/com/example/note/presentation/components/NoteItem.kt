package com.example.note.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.note.domain.model.Note
import com.example.note.presentation.ui.theme.ContentTextStyle
import com.example.note.presentation.ui.theme.DateTextStyle
import com.example.note.presentation.ui.theme.TitleTextStyle

/**
 * Компонент NoteItem — отображает одну карточку заметки с заголовком,
 * содержимым и датой создания.
 *
 * @param note Объект [Note], содержащий данные заметки.
 * @param onClick Обработчик клика по карточке заметки.
 */
@Composable
fun NoteItem(
    note: Note,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.elevatedCardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            // Заголовок заметки
            Text(
                text = note.title,
                style = TitleTextStyle,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(2.dp))
            // Основной текст заметки
            Text(
                text = note.content,
                style = ContentTextStyle
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Отформатированная дата создания
            Text(
                text = note.getFormatDate(),
                style = DateTextStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}