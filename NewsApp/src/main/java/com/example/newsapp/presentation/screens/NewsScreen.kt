package com.example.newsapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.presentation.NewsViewModel.NewsViewModel
import com.example.newsapp.presentation.components.news.NewsList
import com.example.newsapp.presentation.navigation.Screen

@Composable
fun NewsScreen(
    navController: NavController,
) {
    val viewModel: NewsViewModel = viewModel()
    val news by viewModel.newsState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            error != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${stringResource(R.string.error)} $error",
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.loadNews() }) {
                        Text(text = stringResource(R.string.retry))
                    }
                }
            }

            news.isEmpty() -> {
                Text(
                    text = stringResource(R.string.no_news_found),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                NewsList(
                    news = news,
                    onNewsClick = { newsId ->
                        navController.navigate(Screen.DetailsScreen.createRoute(newsId))
                    }
                )
            }
        }
    }
}