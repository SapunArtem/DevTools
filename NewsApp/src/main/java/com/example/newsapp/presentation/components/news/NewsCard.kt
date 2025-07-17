package com.example.newsapp.presentation.components.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsapp.R
import com.example.newsapp.data.api.models.NewsItemDto
import com.example.newsapp.presentation.ui.theme.Description
import com.example.newsapp.presentation.ui.theme.NewsNameInCard

/**
 * NewsCard - Карточка для отображения новости.
 *
 * @param newsItem Данные новости (Results)
 * @param onNewsClick Обработчик клика по карточке
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(
    newsItem: NewsItemDto,
    onNewsClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onNewsClick),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Изображение новости (используется Glide или дефолтное)
            if (!newsItem.icon.isNullOrEmpty()) {
                GlideImage(
                    model = newsItem.icon,
                    contentDescription = newsItem.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Fit
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.news),
                    contentDescription = newsItem.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Описание и описание новости
            Text(
                text = newsItem.description,
                style = Description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Подпись с названием источника,картинкой и датой
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GlideImage(
                        model = newsItem.icon,
                        contentDescription = newsItem.name,
                        modifier = Modifier
                            .height(20.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = newsItem.name,
                        style = NewsNameInCard
                    )
                }
                Text(
                    text = formatNewsDate(newsItem.last_fetch),
                    style = NewsNameInCard
                )
            }
        }
    }
}