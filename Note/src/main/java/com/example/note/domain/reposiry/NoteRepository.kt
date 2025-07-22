package com.example.note.domain.reposiry

import com.example.note.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * Интерфейс репозитория для управления заметками.
 * Абстрагирует доступ к данным .
 */
interface NoteRepository {
    /**
     * Получает все заметки.
     * @return [Flow] списка заметок.
     */
    fun getAllNotes(): Flow<List<Note>>
    /**
     * Добавляет новую заметку.
     * @param note заметка для вставки.
     */
    suspend fun insertNote(note: Note)
    /**
     * Обновляет существующую заметку.
     * @param note заметка с обновлёнными данными.
     */
    suspend fun updateNote(note: Note)
    /**
     * Удаляет указанную заметку.
     * @param note заметка для удаления.
     */
    suspend fun deleteNote(note: Note)
}