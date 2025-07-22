package com.example.note.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.note.data.local.database.NoteDatabase
import com.example.note.data.local.repository.NoteRepositoryImpl
import com.example.note.presentation.screen.EditNoteScreen
import com.example.note.presentation.screen.NotesScreen
import com.example.note.presentation.screen.SettingsScreen
import com.example.note.presentation.viewModel.NotesViewModel
import com.example.note.presentation.viewModel.NotesViewModelFactory

/**
 * Класс, представляющий экраны приложения и их маршруты навигации.
 *
 * @property route Строка маршрута, уникально идентифицирующая экран в навигации.
 */
sealed class Screen(val route: String) {
    /** Экран со списком заметок */
    object Notes : Screen("notes")
    /** Экран для создания новой заметки */
    object AddNote : Screen("add_note")
    /** Экран с настройками приложения */
    object Settings : Screen("settings")
    /**
     * Экран для редактирования заметки, принимает параметр noteId.
     * @param noteId ID заметки для редактирования.
     */
    object EditNote : Screen("edit_note/{noteId}") {
        /**
         * Создает маршрут с подставленным ID заметки.
         *
         * @param noteId ID заметки.
         * @return Маршрут для навигации к экрану редактирования конкретной заметки.
         */
        fun createRoute(noteId: Long) = "edit_note/$noteId"
    }
}

/**
 * Основной компонент навигации приложения.
 * Настраивает навигационные маршруты с использованием [NavHost] и [NavController].
 *
 * Инициализирует [NotesViewModel] с помощью фабрики, передавая DAO заметок.
 * Определяет навигацию между экранами списка заметок, добавления, редактирования и настроек.
 */
@Composable
fun MainNoteNavigation() {
    val navController = rememberNavController()
    // Инициализация ViewModel с репозиторием для работы с заметками
    val viewModel: NotesViewModel = viewModel(
        factory = NotesViewModelFactory(
            NoteRepositoryImpl(
                NoteDatabase.getDatabase(LocalContext.current).noteDao()
            )
        )
    )
    NavHost(
        navController = navController,
        startDestination = Screen.Notes.route
    ) {
        // Экран со списком заметок
        composable(Screen.Notes.route) {
            NotesScreen(
                viewModel = viewModel,
                onNoteClick = { noteId ->
                    navController.navigate(Screen.EditNote.createRoute(noteId))
                },
                onAddNote = {
                    navController.navigate(Screen.AddNote.route)
                },
                onSettingsClick = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }
        // Экран добавления новой заметки (noteId == null)
        composable(Screen.AddNote.route) {
            EditNoteScreen(
                noteId = null,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
        // Экран редактирования заметки с параметром noteId
        composable(
            route = Screen.EditNote.route,
            arguments = listOf(navArgument("noteId") {
                type = NavType.LongType
            })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getLong("noteId") ?: return@composable
            EditNoteScreen(
                noteId = noteId,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
        // Экран настроек приложения
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
    }
}