package com.example.newsapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.api.repository.NewsRepositoryImpl
import com.example.newsapp.domain.model.NewsItem
import com.example.newsapp.domain.usecase.GetNewsSourcesUseCase
import com.example.newsapp.presentation.components.error.errorException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * NewsViewModel - ViewModel для экрана списка новостей.
 * Управляет состоянием загрузки, ошибок и списком новостей.
 */
class NewsViewModel(
    private val getNewsSources : GetNewsSourcesUseCase = GetNewsSourcesUseCase(
        NewsRepositoryImpl()
    )
) : ViewModel() {



    // Состояние списка новостей
    private val _newsListState = MutableStateFlow<NewsListState>(NewsListState.Loading)
    val newsListState: StateFlow<NewsListState> = _newsListState



    /**
     * Загружает список новостей
     */
    fun loadNews() {
        viewModelScope.launch {
            _newsListState.value = NewsListState.Loading
            getNewsSources().onSuccess { news->
                _newsListState.value = NewsListState.Success(news)
            }.onFailure {
                _newsListState.value = NewsListState.Error(errorException(it.message))
            }

        }
    }
}


sealed class NewsListState{
    object Loading : NewsListState()
    data class Success(val news : List<NewsItem>) : NewsListState()
    data class Error(val message : Int) : NewsListState()
}