package com.example.note.data.local.mapper

import com.example.note.data.local.model.NoteEntity
import com.example.note.domain.model.Note

/**
 * Преобразует доменную модель Note в модель базы данных NoteEntity.
 * @receiver Доменная модель заметки
 * @return Модель базы данных NoteEntity
 */
fun Note.toEntity() = NoteEntity(
    id = id ?: 0,
    title = title,
    content = content,
    createdAt = createdAt
)

/**
 * Преобразует модель базы данных NoteEntity в доменную модель Note.
 * @receiver Модель базы данных NoteEntity
 * @return Доменная модель Note
 */
fun NoteEntity.toNote() = Note(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt
)