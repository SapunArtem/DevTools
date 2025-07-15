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
import com.example.newsapp.R
import com.example.newsapp.data.mapper.toDto
import com.example.newsapp.presentation.viewModel.NewsViewModel
import com.example.newsapp.presentation.components.news.NewsList
import com.example.newsapp.presentation.navigation.Screen
import com.example.newsapp.presentation.viewModel.NewsListState

/**
 * NewsScreen - Экран списка новостей.
 *
 * @param navController Контроллер навигации для переходов
 */
@Composable
fun NewsScreen(
    viewModel: NewsViewModel = viewModel(),
    onNewsClick : (String) -> Unit = {}
) {
    val newsState by viewModel.newsListState.collectAsState()



    when(val state = newsState){
        is NewsListState.Loading ->{
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
            viewModel.loadNews()
        }
        is NewsListState.Success ->{
            NewsList(
                news = state.news.map { it.toDto() },
                onNewsClick = onNewsClick,
                modifier = Modifier.padding(16.dp)
            )
        }
        is NewsListState.Error ->{
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(text = state.message)
            }
        }
    }
}