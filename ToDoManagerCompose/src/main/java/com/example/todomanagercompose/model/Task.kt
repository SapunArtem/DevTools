package com.example.todomanagercompose.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Представление задачи, с необходимыми полями
 * @property id id здачи
 * @property title название задачи
 * @property description описание задачи
 * @property isCompleted указатель выполнена ли задача (изначальное значение false)
 * @property createdAt дата создания задачи в формате '17.06.2025'
 */
data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val createdAt: String = SimpleDateFormat(
        "dd.MM.yyyy",
        Locale.getDefault()).format(Date())
)
