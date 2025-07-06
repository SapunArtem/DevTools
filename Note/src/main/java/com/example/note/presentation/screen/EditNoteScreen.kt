package com.example.note.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.note.presentation.ui.theme.ContentTextStyle
import com.example.note.presentation.ui.theme.DateTextStyle
import com.example.note.presentation.ui.theme.TitleTextStyle
import com.example.note.presentation.viewModel.NotesViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
    val noteState by remember(noteId) {
        if (noteId != null) {
            derivedStateOf { viewModel.notes.value.find { it.id == noteId } }
        } else {
            mutableStateOf(null)
        }
    }
    var title by remember { mutableStateOf(noteState?.title ?: "") }
    var content by remember { mutableStateOf(noteState?.content ?: "") }
    val dateText = remember(noteState) {
        noteState?.createdAt?.let {
            SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date(it))
        } ?: SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date(currentDate))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (noteId == null) "Новая заметка" else "Редактировать") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                },
                actions = {
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
        }

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
                textStyle = TitleTextStyle,
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
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
                textStyle = ContentTextStyle,
                minLines = 10,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    }
}