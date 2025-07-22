package com.example.note.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.note.presentation.components.FloatingButton
import com.example.note.presentation.components.NoteItem
import com.example.note.presentation.viewModel.NotesViewModel



/**
 * Экран отображения списка заметок пользователя.
 *
 * @param viewModel Экземпляр [NotesViewModel] для работы с заметками.
 * @param onNoteClick Лямбда, вызываемая при нажатии на заметку, принимает ID заметки.
 * @param onAddNote Лямбда для обработки нажатия кнопки добавления новой заметки.
 * @param onSettingsClick Лямбда для обработки нажатия кнопки перехода в настройки.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    viewModel: NotesViewModel,
    onNoteClick: (Long) -> Unit,
    onAddNote: () -> Unit,
    onSettingsClick: () -> Unit
) {
    // Получаем текущий список заметок из ViewModel
    val notes by viewModel.notes.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text(text = "Мои заметки") },
                actions = {
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Настройки"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingButton(onAddNote)
        },
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),


                ) {
                items(notes) { note ->
                    NoteItem(
                        note = note,
                        onClick = { note.id?.let { onNoteClick(it) } }
                    )
                }
            }
        }
    )

}