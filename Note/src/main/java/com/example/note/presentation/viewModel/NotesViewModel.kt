package com.example.note.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.domain.model.Note
import com.example.note.domain.reposiry.NoteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


/**
 * ViewModel для управления списком заметок.
 *
 * @property repository Репозиторий для операций с заметками.
 */
class NotesViewModel(private val repository: NoteRepository) : ViewModel() {
    /**
     * Поток со списком всех заметок, который подписывается в [viewModelScope].
     * Обновляется при изменениях в репозитории.
     */
    val notes = repository.getAllNotes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    /**
     * Добавляет новую заметку с заданным заголовком и содержимым.
     *
     * @param title Заголовок заметки.
     * @param content Содержимое заметки.
     */
    fun addNote(title: String, content: String) = viewModelScope.launch {
        repository.insertNote(Note(title = title, content = content))
    }

    /**
     * Обновляет существующую заметку.
     *
     * @param note Обновлённая заметка.
     */
    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

    /**
     * Удаляет заметку из репозитория.
     *
     * @param note Заметка для удаления.
     */
    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)

    }
}