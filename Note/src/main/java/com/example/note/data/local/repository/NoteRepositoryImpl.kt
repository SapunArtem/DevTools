package com.example.note.data.local.repository

import com.example.note.data.local.dao.NoteDao
import com.example.note.data.local.mapper.toEntity
import com.example.note.data.local.mapper.toNote
import com.example.note.domain.model.Note
import com.example.note.domain.reposiry.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().map { notes -> notes.map { it.toNote() } }
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insert(note.toEntity())
    }

    override suspend fun updateNote(note: Note) {
        noteDao.update(note.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.delete(note.toEntity())
    }
}