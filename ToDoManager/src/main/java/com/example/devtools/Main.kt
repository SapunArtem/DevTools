package com.example.devtools


import kotlinx.coroutines.runBlocking
import java.util.Scanner

fun main() = runBlocking {
    val todoManager = ToDoManager()
    val scanner = Scanner(System.`in`)

    println("Добро пожаловать в TO-DO Manager!!!")

    while (true) {
        println("\nВыберите действия:")
        println("1. Создание задачи")
        println("2. Показать все задачи")
        println("3. Отметить задачу как выполненную")
        println("4. Удалить задачу")
        println("5. Выйти")
        println(">")


        when (scanner.nextLine()) {
            "1" -> {
                print("Введите название задачи:")
                val title = scanner.nextLine()
                todoManager.addTask(title)
            }

            "2" -> {
                todoManager.showAllTasks()
            }

            "3" -> {
                print("Введите ID задачи для отметки как выполненной:")
                val id = scanner.nextLine().toIntOrNull()
                if (id != null) {
                    todoManager.completedTask(id)
                } else {
                    println("❌Ошибка: Введите корректный ID")
                }
            }

            "4" -> {
                print("Введите ID задачи для удаления:")
                val id = scanner.nextLine().toIntOrNull()
                if (id != null) {
                    todoManager.deleteTask(id)
                } else {
                    println("❌Ошибка: Введите корректный ID")
                }
            }

            "5" -> {
                println("До свидания!")
                return@runBlocking
            }

            else -> println("❌Ошибка: неверный ввод")
        }
    }
}