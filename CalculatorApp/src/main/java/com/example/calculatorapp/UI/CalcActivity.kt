package com.example.calculatorapp.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatorapp.Calculator
import com.example.calculatorapp.R
import com.example.calculatorapp.databinding.ActivityCalculatorBinding

/**
 * Класс экрана калькулятора
 */
class CalcActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val calculator = Calculator()
    private var lastResult = ""
    private val calculationHistory = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNumberButtons()
        setupOperationButtons()
        setupOtherButtons()
        loadHistory()
    }

    /**
     * Функция необходим для обработки кнопок чисел
     */
    private fun setupNumberButtons() {
        with(binding) {
            listOf(
                btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9
            ).forEach { button ->
                button.setOnClickListener {
                    updateDisplay(calculator.processInput(button.text.toString()))
                }
            }
        }
    }

    /**
     * Функция необходим для обработки кнопок математичесских символов
     */
    private fun setupOperationButtons() {
        with(binding) {
            listOf(
                btnMinus, btnDivision, btnMultiplication, btnPlus
            ).forEach { button ->
                button.setOnClickListener {
                    when (button) {
                        btnMinus -> updateDisplay(calculator.processInput("-"))
                        btnProcent -> updateDisplay(calculator.processInput("%"))
                        btnDivision -> updateDisplay(calculator.processInput("/"))
                        btnMultiplication -> updateDisplay(calculator.processInput("*"))
                        btnPlus -> updateDisplay(calculator.processInput("+"))
                    }
                }
            }
        }
    }
    /**
     * Функция необходим для обработки кнопок : History,AC,Del,Comma,Equals
     */
    private fun setupOtherButtons() {
        with(binding) {
            listOf(
                btnAC, btnDel, btnComma, btnEquals, btnHistory
            ).forEach { button ->
                button.setOnClickListener {
                    when (button) {
                        btnAC -> updateDisplay(calculator.processInput("AC"))
                        btnDel -> updateDisplay(calculator.processInput("DEL"))
                        btnComma -> updateDisplay(calculator.processInput("."))
                        btnEquals -> {
                            val result = calculator.processInput("=")
                            calculationHistory.add("${tvOperation.text} = $result")
                            lastResult = result
                            updateDisplay(result)
                            saveHistory()
                            tvAnswer.textSize = 45F
                            tvOperation.textSize = 25F
                        }

                        btnHistory -> {
                                /*val intent = Intent(
                                    this@CalcActivity, HistoryActivity::class.java
                                )
                                startActivity(intent)*/
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.historyFragmentConteiner, HistoryFragment())
                                    .addToBackStack(null)
                                    .commit()
                            }
                        }
                    }
                }
            }
        }


    /**
     * Функция необходим для обновления данных в строке вывода и ответа
     */
    private fun updateDisplay(text: String) {

        with(binding) {
            tvOperation.text = text
            if (text == "") {
                tvAnswer.text = ""
            } else {
                tvAnswer.text = "=$text"
            }

                tvOperation.textSize = 45F
                tvAnswer.textSize = 25F


        }
    }
    /**
     * Функция необходим для загрузки данных в SharedPreferences
     */
    private fun saveHistory() {
        val prefs = getSharedPreferences("calculator_prefs", MODE_PRIVATE)
        val editor = prefs.edit()
        val historyString = calculationHistory.joinToString("||")
        editor.putString("history", historyString)
        editor.apply()
    }

    /**
     * Класс необходим для загрузки данных из SharedPreferences
     */
    private fun loadHistory() {
        val prefs = getSharedPreferences("calculator_prefs", MODE_PRIVATE)
        val historyString = prefs.getString("history", "") ?: ""
        calculationHistory.clear()
        if (historyString.isNotEmpty()) {
            calculationHistory.addAll(historyString.split("||"))
        }

    }


}
