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
import com.example.todomanagercompose.model.Task
import com.example.todomanagercompose.ui.theme.MainContainer
import com.example.todomanagercompose.ui.theme.OrangeForAddButton

/**
 * Главный компонент приложения для управления задачами (To-Do Manager).
 *
 * Этот компонент предоставляет:
 * - Отображение списка задач
 * - Добавление новых задач через диалоговое окно
 * - Удаление задач
 * - Отметку задач как выполненных
 *
 * @sample com.example.todomanagercompose.preview.ToDoManagerPreview
 * @see androidx.compose.material3.Scaffold
 * @see androidx.compose.material3.FloatingActionButton
 */
@Composable
fun ToDoManagerApp() {
    // Состояние списка задач. Используем mutableStateOf для реактивного обновления UI
    var tasks by remember { mutableStateOf<List<Task>>(emptyList()) }

    // Счетчик для генерации уникальных ID новых задач
    var newTaskId by remember { mutableIntStateOf(1) }

    // Состояние полей формы для новой задачи
    var newTaskTitle by remember { mutableStateOf("") }  // Название задачи (обязательное поле)
    var newTaskDescription by remember { mutableStateOf("") }  // Описание задачи (необязательное)

    // Состояние видимости диалогового окна добавления новой задачи
    var showDialog by remember { mutableStateOf(false) }

    /**
     * Функция для добавления новой задачи в список.
     *
     * Проверяет, что поле названия не пустое, затем:
     * 1. Создает новую задачу с уникальным ID
     * 2. Добавляет задачу в список
     * 3. Сбрасывает поля формы
     * 4. Закрывает диалоговое окно
     */
    fun addTask() {
        if (newTaskTitle.isNotBlank()) {
            tasks = tasks + Task(
                id = newTaskId++,  // Инкрементируем ID для следующей задачи
                title = newTaskTitle,
                description = newTaskDescription
            )
            // Сбрасываем поля формы
            newTaskTitle = ""
            newTaskDescription = ""
            showDialog = false
        }
    }

    // Используем Scaffold как основу для Material Design интерфейса
    Scaffold(
        // Верхняя панель приложения
        topBar = { MainTopAppBar() },

        // Основное содержимое экрана
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)  // Учитываем системные отступы
                    .fillMaxSize()
                    .background(MainContainer)  // Фон основного контейнера
            ) {
                when {
                    // Если список задач пуст - показываем подсказку
                    tasks.isEmpty() -> {
                        Text(
                            text = "Нет задач, добавьте первую!",
                            modifier = Modifier.align(Alignment.Center),
                            color = Color.Gray
                        )
                    }

                    // Отображаем список задач
                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(
                                items = tasks,
                                key = { it.id }  // Уникальный ключ для правильной работы анимаций
                            ) { task ->
                                // Компонент отдельной задачи
                                ItemList(
                                    task = task,
                                    onDelete = {
                                        // Удаляем задачу по ID (создаем новый список без этой задачи)
                                        tasks = tasks.filter { it.id != task.id }
                                    },
                                    onCheckedChange = { isChecked ->
                                        // Обновляем статус выполнения задачи
                                        tasks = tasks.map { currentTask ->
                                            if (currentTask.id == task.id) {
                                                currentTask.copy(isCompleted = isChecked)
                                            } else {
                                                currentTask
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        },

        // Кнопка "Добавить" в правом нижнем углу
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },  // Показываем диалог при клике
                containerColor = OrangeForAddButton,  // Цвет кнопки
                contentColor = Color.White,  // Цвет иконки
                shape = CircleShape,  // Круглая форма
                elevation = FloatingActionButtonDefaults.elevation(8.dp),  // Тень
                modifier = Modifier
                    .navigationBarsPadding()  // Учет системной панели навигации
                    .size(110.dp)  // Размер кнопки
                    .padding(end = 30.dp, bottom = 30.dp)  // Отступы от краев
            ) {
                Icon(
                    imageVector = Icons.Default.Add,  // Иконка "+"
                    contentDescription = "Добавление новой задачи",  // Описание для accessibility
                    modifier = Modifier.size(50.dp)  // Размер иконки
                )
            }
        }
    )

    // Диалоговое окно для добавления новой задачи
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },  // Закрытие при клике вне диалога
            title = { Text("Новая задача") },  // Заголовок диалога
            text = {
                Column {
                    // Поле ввода названия задачи
                    OutlinedTextField(
                        value = newTaskTitle,
                        onValueChange = { newTaskTitle = it },
                        label = { Text("Название задачи*") },  // Подсказка
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true  // Одна строка для названия
                    )

                    // Поле ввода описания
                    OutlinedTextField(
                        value = newTaskDescription,
                        onValueChange = { newTaskDescription = it },
                        label = { Text("Описание") },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 3  // Несколько строк для описания
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { addTask() },  // Вызов функции добавления
                    enabled = newTaskTitle.isNotBlank()  // Активна только при заполненном названии
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