package com.example.todomanagercompose.ui_compnents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todomanagercompose.Task
import com.example.todomanagercompose.ui.theme.MainContainer
import com.example.todomanagercompose.ui.theme.OrangeForAddButton

@Composable
fun ToDoManagerApp() {
    var tasks by remember { mutableStateOf<List<Task>>(emptyList()) }
    var newTaskId by remember { mutableIntStateOf(1) }
    var newTaskTitle by remember { mutableStateOf("") }
    var newTaskDescription by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }


    fun addTask() {
        if (newTaskTitle.isNotBlank()) {
            tasks = tasks + Task(
                id = newTaskId++,
                title = newTaskTitle,
                description = newTaskDescription
            )
            newTaskTitle = ""
            newTaskDescription = ""
            showDialog = false
        }
    }

    Scaffold(
        topBar = { MainTopAppBar() },
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .background(MainContainer)
            ) {
                if (tasks.isEmpty()) {
                    Text(
                        text = "Нет задач, добавьте первую!",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Gray
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(
                            items = tasks,
                            key = { it.id }
                        ) { task ->
                            ItemList(
                                task = task,
                                onDelete = { tasks = tasks.filter { it.id != task.id } },
                                onCheckedChange = { isChecked ->
                                    tasks = tasks.map {
                                        if (it.id == task.id) it.copy(isCompleted = isChecked) else it
                                    }
                                }
                            )
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = OrangeForAddButton,
                contentColor = Color.White,
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(8.dp),
                modifier = Modifier
                    .navigationBarsPadding()
                    .size(110.dp)
                    .padding(end = 30.dp, bottom = 30.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавление новой задачи",
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    )

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Новая задача") },
            text = {
                Column {
                    OutlinedTextField(
                        value = newTaskTitle,
                        onValueChange = { newTaskTitle = it },
                        label = { Text("Название задачи*") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = newTaskDescription,
                        onValueChange = { newTaskDescription = it },
                        label = { Text("Описание") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { addTask() },
                    enabled = newTaskTitle.isNotBlank()
                ) {
                    Text("Добавить")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Отмена")
                }
            }
        )
    }

}