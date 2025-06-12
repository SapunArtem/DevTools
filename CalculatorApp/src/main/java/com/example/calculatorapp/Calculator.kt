package com.example.calculatorapp

class Calculator {
    private var currentInput = StringBuilder()
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
            "=" -> calculate()
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
     * Функция для обработки математических выражений
     */
    private fun calculate(): String {
        val split = currentInput.split(Regex("(?<=[+\\-*/])|(?=[+\\-*/])"))
            .filter { it.isNotEmpty() }

        val tokens = mutableListOf<Any>()
        split.forEach { element ->
            when (element) {
                "*", "/", "-", "+" -> tokens.add(element)
                else -> if (element.contains(".")) {
                    tokens.add(element.toDouble())
                } else {
                    tokens.add(element.toDouble())
                }

            }
        }

        val highPriority = mutableListOf<Any>()
        var i = 0
        while (i < tokens.size) {
            when (val token = tokens[i]) {
                is Int -> highPriority.add(token)
                "*", "/" -> {
                    val prev = highPriority.removeLastOrNull() as Double
                    val next = tokens[i + 1] as Double
                    val result = when (token) {
                        "*" -> prev * next
                        "/" -> {
                            try {
                                if (next == 0.0) throw ArithmeticException("Деление на ноль")
                                prev / next
                            } catch (e: ArithmeticException) {
                                return "Ошибка: ${e.message}"
                            }
                        }

                        else -> throw IllegalStateException()
                    }
                    highPriority.add(result)
                    i++
                }

                else -> highPriority.add(token)
            }
            i++
        }

        var result: Double = highPriority[0] as Double
        i = 1
        while (i < highPriority.size) {
            when (highPriority[i]) {
                "+" -> {
                    val next = highPriority[i + 1] as Double
                    result += next

                }

                "-" -> {
                    val next = highPriority[i + 1] as Double
                    result -= next
                }
            }
            i += 2
        }

        return if (result % 1.0 == 0.0) result.toInt().toString() else result.toString()
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