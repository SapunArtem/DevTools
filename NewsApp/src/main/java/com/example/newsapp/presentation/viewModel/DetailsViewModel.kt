package com.example.newsapp.presentation.viewModel

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.api.repository.NewsRepositoryImpl
import com.example.newsapp.domain.model.NewsItem
import com.example.newsapp.domain.usecase.GetNewsDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * DetailsViewModel - ViewModel для экрана деталей новости.
 * Загружает и хранит данные конкретной новости.
 */
class DetailsViewModel : ViewModel() {
    private val getNewsDetails = GetNewsDetailsUseCase(
        NewsRepositoryImpl()
    )

    private val _newsState = MutableStateFlow<NewsDetailsState>(NewsDetailsState.Loading)
    val newsState: StateFlow<NewsDetailsState> = _newsState

    fun loadNewsDetails(newsId : String){
        viewModelScope.launch {
            _newsState.value = NewsDetailsState.Loading
            getNewsDetails(newsId).onSuccess { news->
                _newsState.value = NewsDetailsState.Success(news)
            }.onFailure {
                _newsState.value = NewsDetailsState.Error(it.message ?: "Unknow error")
            }
        }
    }

    fun openInBrowser(context: Context,uri : String){
        val intent = Intent(Intent.ACTION_VIEW,uri.toUri())
        context.startActivity(intent)
    }
}

sealed class NewsDetailsState{
    object Loading : NewsDetailsState()
    data class Success(val news : NewsItem) : NewsDetailsState()
    data class Error(val message : String) : NewsDetailsState()
}