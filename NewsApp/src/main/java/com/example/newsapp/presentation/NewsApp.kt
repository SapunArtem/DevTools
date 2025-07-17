package com.example.newsapp.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.newsapp.presentation.components.settings.app_language.LocalizationManager
import com.example.newsapp.presentation.screens.App
import com.example.newsapp.presentation.ui.theme.NewsAppTheme

class NewsApp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) { App() }

            }
        }
    }


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocalizationManager.wrapContext(newBase))
    }
}

