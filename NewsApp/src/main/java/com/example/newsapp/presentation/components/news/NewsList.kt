package com.example.newsapp.presentation.components.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsapp.data.api.models.NewsItemDto

/**
 * NewsList - Список новостных карточек с LazyColumn.
 *
 * @param news Список новостей для отображения
 * @param onNewsClick Обработчик клика по новости (передает ID новости)
 */
@Composable
fun NewsList(
    news: List<NewsItemDto>,
    onNewsClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(news) { newsItem ->
            NewsCard(
                newsItem = newsItem,
                onNewsClick = { onNewsClick(newsItem.id) }
            )
        }
    }
}