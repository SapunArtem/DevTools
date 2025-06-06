package com.example.calculatorapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatorapp.databinding.ActivityHistoryBinding

/**
 * Класс экрана Истории
 */
class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadAndShowHistory()

        binding.btnDeleteHistory.setOnClickListener {
            clearHistory()
        }
    }

    /**
     * Функция для загрузки и отображения истории из sharedpreferences
     */
    private fun loadAndShowHistory() {
        val prefs = getSharedPreferences("calculator_prefs", MODE_PRIVATE)
        val historyString = prefs.getString("history", "") ?: ""
        val historyList = if (historyString.isNotEmpty()) {
            historyString.split("||")
        } else {
            emptyList()
        }
        binding.tvHistory.text = historyList.joinToString("\n\n")
    }
    /**
     * Функция для очистки истории
     */
    private fun clearHistory() {
        val prefs = getSharedPreferences("calculator_prefs", MODE_PRIVATE)
        val editor = prefs.edit()
        editor.remove("history")
        editor.apply()
        binding.tvHistory.text = ""
    }
}