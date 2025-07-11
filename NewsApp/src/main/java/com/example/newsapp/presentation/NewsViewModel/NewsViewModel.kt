package com.example.newsapp.presentation.NewsViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.api.NewsRepository
import com.example.newsapp.data.api.models.Results
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repository = NewsRepository()

    private val _newsState = MutableStateFlow<List<Results>>(emptyList())
    val newsState: StateFlow<List<Results>> = _newsState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()


    init {
        loadNews()
    }


    fun loadNews() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val results = repository.getResults()
                _newsState.value = results.results
            } catch (e: Exception) {
                _error.value = e.message ?: "UnknownError"
            } finally {
                _isLoading.value = false
            }
        }
    }
}