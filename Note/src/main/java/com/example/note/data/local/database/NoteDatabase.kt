package com.example.note.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.note.data.local.dao.NoteDao
import com.example.note.data.local.model.NoteEntity

/**
 * Абстрактный класс базы данных Room для хранения заметок.
 * Определяет сущности и версию базы данных, предоставляет доступ к DAO.
 */
@Database(
    entities = [NoteEntity::class],         // Список сущностей базы данных
    version = 1,                            // Версия схемы базы данных
    exportSchema = true                     // Флаг экспорта схемы для версионности
)
abstract class NoteDatabase : RoomDatabase() {
    /**
     * Получает реализацию NoteDao для работы с заметками.
     * @return Экземпляр NoteDao
     */
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        /**
         * Получает экземпляр базы данных (реализация паттерна Singleton).
         * @param context Контекст приложения
         * @return Экземпляр NoteDatabase
         */
        fun getDatabase(context: Context): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "notes_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}