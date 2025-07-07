package com.example.note.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.note.data.local.model.NoteEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) для работы с заметками в базе данных.
 * Предоставляет методы для основных CRUD операций.
 */
@Dao
interface NoteDao {
    /**
     * Вставляет новую заметку в базу данных.
     * @param noteEntity Заметка для вставки
     * @return ID вставленной заметки
     */
    @Insert
    suspend fun insert(noteEntity: NoteEntity): Long

    /**
     * Обновляет существующую заметку в базе данных.
     * @param noteEntity Заметка с обновленными данными
     */
    @Update
    suspend fun update(noteEntity: NoteEntity)

    /**
     * Удаляет заметку из базы данных.
     * @param noteEntity Заметка для удаления
     */
    @Delete
    suspend fun delete(noteEntity: NoteEntity)

    /**
     * Получает все заметки из базы данных, отсортированные по дате создания (новые сначала).
     * @return Flow со списком заметок
     */
    @Query("SELECT * FROM notes ORDER BY createdAt DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>
}