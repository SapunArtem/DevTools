package com.example.note.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.note.domain.reposiry.NoteRepository

/**
 * Фабрика для создания экземпляров [NotesViewModel] с передачей репозитория.
 *
 * @property repository Репозиторий заметок, передаваемый в ViewModel.
 */
class NotesViewModelFactory(
    private val repository: NoteRepository
) : ViewModelProvider.Factory {
    /**
     * Создаёт экземпляр ViewModel указанного класса.
     *
     * @throws IllegalArgumentException если передан неизвестный класс ViewModel.
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}