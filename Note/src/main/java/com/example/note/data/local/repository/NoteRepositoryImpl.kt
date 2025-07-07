package com.example.note.data.local.repository

import com.example.note.data.local.dao.NoteDao
import com.example.note.data.local.mapper.toEntity
import com.example.note.data.local.mapper.toNote
import com.example.note.domain.model.Note
import com.example.note.domain.reposiry.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Реализация NoteRepository для работы с локальным хранилищем (Room Database).
 * @property noteDao DAO для доступа к данным заметок
 */
class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {

    /**
     * Получает все заметки из базы данных.
     * @return Flow со списком доменных моделей Note
     */
    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().map { notes -> notes.map { it.toNote() } }
    }

    /**
     * Добавляет новую заметку в базу данных.
     * @param note Заметка для добавления
     */
    override suspend fun insertNote(note: Note) {
        noteDao.insert(note.toEntity())
    }

    /**
     * Обновляет существующую заметку в базе данных.
     * @param note Заметка с обновленными данными
     */
    override suspend fun updateNote(note: Note) {
        noteDao.update(note.toEntity())
    }

    /**
     * Удаляет заметку из базы данных.
     * @param note Заметка для удаления
     */
    override suspend fun deleteNote(note: Note) {
        noteDao.delete(note.toEntity())
    }
}