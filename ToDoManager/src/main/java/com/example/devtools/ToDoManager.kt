package com.example.devtools

import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

/**
 * Делает первую букву заглавное
 * Пример:
 * "вода".capitalizeFirstLetter() // "Вода"
 */
fun String.capitalizeFirstLatter(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

}

/**
 * Проверяет валидность введенной строки
 * Строка должа содержать от 3 до 100 символов
 */
fun String.isValidTaskTitle(): Boolean {
    return this.isNotBlank() && this.length in 3..100
}

class ToDoManager {
    private val tasks = mutableListOf<Task>()
    private var nextId = 1

    /**
     * Добавляет новую задачу
     * @param title Навание задачи(3-100 символов)
     */
    suspend fun addTask(title: String) {
        println("Добавление задачи..")
        delay(1.seconds)

        if (title.isValidTaskTitle()) {
            val task = Task(nextId++, title.capitalizeFirstLatter())
            tasks.add(task)
            println("✅Задача '${task.title}' добавлена с ID ${task.id}")
        } else {
            println("❌ Ошибка: Название должно быть от 3 до 100 символов")
        }
    }

    /**
     * Отображает список всех задач
     */
    suspend fun showAllTasks() {
        println("Загрузка задач..")
        delay(1.seconds)

        if (tasks.isEmpty()) {
            println("\uD83D\uDCCB Список задач пуст")
        } else {
            println("\uD83D\uDCCB Список задач:")
            tasks.forEach { task ->
                val status = if (task.isCompleted) "✓" else "✗"
                println("${task.id}. [$status] ${task.title} (создано: ${task.createdAt})")
            }
        }
    }

    /**
     * Отмечает задачу как выполненную
     * @param id ID задачи
     */
    suspend fun completedTask(id: Int) {
        println("обновление статуса задачи..")
        delay(1.seconds)

        val task = tasks.find { it.id == id }
        if (task != null) {
            task.isCompleted = true
            println("✅Задача '${task.title}' отмечена как выполненная")
        } else {
            println("❌Ошибка: задача с ID $id не найдена")
        }
    }

    /**
     * Удаляет задачу
     * @param id ID задачи
     */
    suspend fun deleteTask(id: Int) {
        println("Удаление задачи..")
        delay(1.seconds)

        val task = tasks.find { it.id == id }
        if (task != null) {
            tasks.remove(task)
            println("✅Задача '${task.title}' удалена")
        } else {
            println("❌Ошибка: задача с ID $id не найдена")
        }
    }
}