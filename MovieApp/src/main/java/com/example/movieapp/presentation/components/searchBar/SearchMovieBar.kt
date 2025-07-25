package com.example.movieapp.presentation.components.searchBar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.movieapp.presentation.ui.theme.Orange
import com.example.movieapp.presentation.viewModel.MoviesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchMovieBar(
    viewModel: MoviesViewModel
){
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    SearchBar(
        modifier = Modifier
            .fillMaxWidth(),
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = {query = it},
                onSearch = {
                    viewModel.searchMovies(query)
                    active = false
                },
                expanded = active,
                onExpandedChange = {active = it},
                placeholder = {
                    Text(
                        text = "Поиск...",
                        color = Orange
                    ) },
                leadingIcon = {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = Orange
                    ) },
                trailingIcon = {
                    if (active && query.isNotEmpty()){
                        IconButton(onClick = {query = ""}) {
                            Icon(
                                Icons.Filled.Clear,
                                contentDescription = "Clear",
                                tint = Orange
                            )
                        }
                    }
                },
                colors = SearchBarDefaults.inputFieldColors(
                    focusedTextColor = Orange,
                    unfocusedTextColor = Orange,
                    cursorColor = Orange
                )

            )
        },
        expanded = active,
        onExpandedChange = {active = it},
        colors = SearchBarDefaults.colors(
            containerColor = Color.Black
        )

    ){ }
}



