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

sealed class Screen(val route: String) {
    object Notes : Screen("notes")
    object AddNote : Screen("add_note")
    object Settings : Screen("settings")
    object EditNote : Screen("edit_note/{noteId}") {
        fun createRoute(noteId: Long) = "edit_note/$noteId"
    }
}

@Composable
fun MainNoteNavigation() {
    val navController = rememberNavController()
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
        composable(Screen.AddNote.route) {
            EditNoteScreen(
                noteId = null,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
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
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
    }
}