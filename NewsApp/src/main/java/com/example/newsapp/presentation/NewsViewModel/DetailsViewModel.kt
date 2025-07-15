package com.example.newsapp.presentation.NewsViewModel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.api.NewsRepository
import com.example.newsapp.data.api.models.Results
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.core.net.toUri

/**
 * DetailsViewModel - ViewModel для экрана деталей новости.
 * Загружает и хранит данные конкретной новости.
 */
class DetailsViewModel : ViewModel() {
    private val repository = NewsRepository()
    private val _newsDetails = MutableStateFlow<Results?>(null)
    val newsDetails: StateFlow<Results?> = _newsDetails

    /**
     * Загружает детали новости по ID
     * @param newsId Идентификатор новости
     */
    fun loadNewsDetails(newsId: String) {
        viewModelScope.launch {
            try {
                val newssList = repository.getResults().results
                _newsDetails.value = newssList.find { it.id == newsId }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
    fun openInBrowser(context: Context,url : String){
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        context.startActivity(intent)
    }
}