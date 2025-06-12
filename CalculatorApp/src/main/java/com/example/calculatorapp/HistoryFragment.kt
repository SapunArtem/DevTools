package com.example.calculatorapp

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.example.calculatorapp.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater,container,false)
        sharedPreferences = requireContext()
            .getSharedPreferences("calculator_prefs", MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpButtons()
        loadAndShowHistory()
    }

    private fun setUpButtons(){
        with(binding){
            listOf(
                btnBack,btnDeleteHistory
            ).forEach{button ->
                button.setOnClickListener {
                    when(button){
                        btnDeleteHistory -> clearHistory()
                        btnBack ->  parentFragmentManager.popBackStack()
                    }
                }
            }
        }
    }
    /**
     * Функция для загрузки и отображения истории из sharedpreferences
     */
    private fun loadAndShowHistory() {
        val historyString = sharedPreferences.getString("history", "") ?: ""
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
        val editor = sharedPreferences.edit()
        editor.remove("history")
        editor.apply()
        binding.tvHistory.text = ""
    }


}