package com.example.note.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.note.presentation.ui.theme.AppTheme
import com.example.note.presentation.ui.theme.ContentTextStyle
import com.example.note.presentation.ui.theme.DateTextStyle
import com.example.note.presentation.ui.theme.Orange
import com.example.note.presentation.ui.theme.TitleTextStyle
import com.example.note.presentation.viewModel.NotesViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Экран для создания новой заметки или редактирования существующей.
 *
 * @param noteId ID заметки для редактирования. Если null, создаётся новая заметка.
 * @param viewModel Экземпляр [NotesViewModel] для работы с заметками.
 * @param onBack Лямбда-функция, вызываемая при возврате назад (например, по кнопке назад).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreen(
    noteId: Long?,
    viewModel: NotesViewModel,
    onBack: () -> Unit
) {
    val currentDate = remember { System.currentTimeMillis() }
    var isTitleFocused by remember { mutableStateOf(false) }
    var isContentFocused by remember { mutableStateOf(false) }

    // Получаем заметку из ViewModel по ID, если он задан
    val noteState by remember(noteId) {
        if (noteId != null) {
            derivedStateOf { viewModel.notes.value.find { it.id == noteId } }
        } else {
            mutableStateOf(null)
        }
    }

    // Состояния для заголовка и содержимого заметки
    var title by remember { mutableStateOf(noteState?.title ?: "") }
    var content by remember { mutableStateOf(noteState?.content ?: "") }

    // Форматируем дату создания или текущую дату
    val dateText = remember(noteState) {
        noteState?.createdAt?.let {
            SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date(it))
        } ?: SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date(currentDate))
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text(if (noteId == null) "Новая заметка" else "Редактировать") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                },
                actions = {
                    // Кнопка удаления для существующей заметки
                    if (noteId != null) {
                        IconButton(
                            onClick = {
                                noteState?.let {
                                    viewModel.deleteNote(it)
                                    onBack()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Удалить заметку",
                            )
                        }
                    }
                    // Кнопка сохранения заметки
                    IconButton(
                        onClick = {
                            if (title.isNotBlank()) {
                                if (noteId == null) {
                                    viewModel.addNote(title, content)
                                } else {
                                    noteState?.let {
                                        viewModel.updateNote(
                                            it.copy(
                                                title = title,
                                                content = content
                                            )
                                        )
                                    }
                                }
                                onBack()
                            }
                        }
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "Сохранить")
                    }
                }
            )
        },


        ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { if (!isTitleFocused && title.isEmpty()) Text("Заголовок") },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        isTitleFocused = focusState.isFocused
                    },
                textStyle = TitleTextStyle.copy(
                    color = if (AppTheme.isDarkTheme) Orange else Color.Black
                ),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),

                )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = dateText,
                style = DateTextStyle
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = content,
                onValueChange = { content = it },
                label = { if (!isContentFocused && content.isEmpty()) Text("Текст заметки") },
                modifier = Modifier
                    .fillMaxSize()
                    .onFocusChanged { focusState ->
                        isContentFocused = focusState.isFocused
                    },
                textStyle = ContentTextStyle.copy(
                    color = if (AppTheme.isDarkTheme) Orange else Color.Black
                ),
                minLines = 10,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    }
}