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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.movieapp.R
import com.example.movieapp.presentation.ui.theme.Orange
import com.example.movieapp.presentation.viewModel.MoviesViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchMovieBar(
    viewModel: MoviesViewModel,
    onSearch: (String) -> Unit
) {
    val lastQuery by viewModel.lastQuery.collectAsState()
    var query by remember { mutableStateOf(lastQuery) }
    var active by remember { mutableStateOf(false) }

    var debouncedQuery by remember { mutableStateOf(query) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(lastQuery) {
        if (query != lastQuery) {
            query = lastQuery
        }
    }

    LaunchedEffect(query) {
        if (query.length > 2 || query.isEmpty()) {
            scope.launch {
                delay(500)
                debouncedQuery = query
            }
        }
    }

    LaunchedEffect(debouncedQuery) {
        if (debouncedQuery != lastQuery) {
            onSearch(debouncedQuery)
        }
    }

    SearchBar(
        modifier = Modifier
            .fillMaxWidth(),
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = { query = it },
                onSearch = {
                    active = false
                    onSearch(query)
                },
                expanded = active,
                onExpandedChange = { active = it },
                placeholder = {
                    Text(
                        text = "${stringResource(R.string.search)}...",
                        color = Orange
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = Orange
                    )
                },
                trailingIcon = {
                    if (active && query.isNotEmpty()) {
                        IconButton(onClick = { query = "" }) {
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
        onExpandedChange = { active = it },
        colors = SearchBarDefaults.colors(
            containerColor = Color.Black
        )

    ) { }
}



