package com.example.calculatorapp


class Calculator {
    private var currentInput = StringBuilder()
    private val calculate = CalculateExpression()
    private var lastNumber = false

    /**
     * Функция для обработки введенных пользователем символов
     * @param input передается из CalcActivity.kt
     */
    fun processInput(input: String): String {
        return when (input) {
            "AC" -> clear()
            "DEL" -> deleteLastChar()
            "." -> addComma()
            "=" -> calculate.calculate(currentInput)
            else -> {
                if (input.matches(Regex("[0-9]"))) {
                    addNumber(input)
                } else if (input.matches(Regex("[-+/*]"))) {
                    addOperation(input)
                } else {
                    currentInput.toString()
                }
            }
        }
    }

    /**
     * Функция для очистки экрана ввода и ответа
     */
    private fun clear(): String {
        currentInput.clear()
        return currentInput.toString()
    }

    /**
     * Функция для удаления последнего введенного символа
     */
    private fun deleteLastChar(): String {
        if (currentInput.isNotEmpty()) {
            currentInput.deleteCharAt(currentInput.length - 1)
        }
        return currentInput.toString()
    }

    /**
     * Функция для добавления точки
     */
    private fun addComma(): String {
        if (lastNumber) currentInput.append(".")
        lastNumber = false
        return currentInput.toString()
    }

    /**
     * Функция для добавления цифр
     * @param number передает числа в виде String
     */
    private fun addNumber(number: String): String {
        currentInput.append(number)
        lastNumber = true
        return currentInput.toString()
    }

    /**
     * Функция для добавления математических операндов
     * @param operation передает математические символы в виде String
     */
    private fun addOperation(operation: String): String {
        if (lastNumber) currentInput.append(operation)
        lastNumber = false
        return currentInput.toString()
    }


}