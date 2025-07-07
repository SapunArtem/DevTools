package com.example.note.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Модель данных для хранения заметки в базе данных.
 * @property id Уникальный идентификатор заметки (автоинкремент)
 * @property title Заголовок заметки
 * @property content Содержимое заметки
 * @property createdAt Дата создания в формате timestamp
 */
@Entity(tableName = "notes")                    // Название таблицы в базе данных
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)            // Автоматическая генерация ID
    val id: Long = 0,
    val title: String,
    val content: String,
    val createdAt: Long
)
