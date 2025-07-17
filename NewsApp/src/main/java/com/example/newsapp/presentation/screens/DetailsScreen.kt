package com.example.newsapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsapp.R
import com.example.newsapp.presentation.viewModel.DetailsViewModel
import com.example.newsapp.presentation.viewModel.NewsDetailsState

/**
 * Экран с деталями новости.
 *
 * @param newsId идентификатор новости для загрузки деталей
 * @param viewModel ViewModel для управления состоянием деталей новости
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsScreen(
    newsId: String,
    viewModel: DetailsViewModel = viewModel()
) {
    val newsState by viewModel.newsState.collectAsState()
    val context = LocalContext.current

    // Загрузка данных при изменении newsId
    LaunchedEffect(key1 = newsId) {
        viewModel.loadNewsDetails(newsId)
    }
    when (val state = newsState) {
        is NewsDetailsState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is NewsDetailsState.Success -> {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                GlideImage(
                    model = state.news.icon,
                    contentDescription = state.news.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = state.news.name,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${stringResource(R.string.category)} ${state.news.category.joinToString()}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = state.news.description,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        viewModel.openInBrowser(context, state.news.url)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.secondary,
                    ),
                    elevation = ButtonDefaults.buttonElevation(3.dp)
                ) {
                    Text(
                        text = stringResource(R.string.open_in_browser)
                    )
                }
            }
        }

        is NewsDetailsState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.message
                )
            }
        }
    }

}