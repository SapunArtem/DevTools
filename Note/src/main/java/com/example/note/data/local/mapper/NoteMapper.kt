package com.example.note.data.local.mapper

import com.example.note.data.local.model.NoteEntity
import com.example.note.domain.model.Note

fun Note.toEntity() = NoteEntity(
    id = id ?: 0,
    title = title,
    content = content,
    createdAt = createdAt
)

fun NoteEntity.toNote() = Note(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt
)