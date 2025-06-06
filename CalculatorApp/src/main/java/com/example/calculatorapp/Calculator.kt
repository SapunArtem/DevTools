package com.example.calculatorapp

class Calculator {
    private var currentInput = StringBuilder()
    private var lastNumber = false


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

    private fun clear(): String {
        currentInput.clear()
        return currentInput.toString()
    }

    private fun deleteLastChar(): String {
        if (currentInput.isNotEmpty()) {
            currentInput.deleteCharAt(currentInput.length - 1)
        }
        return currentInput.toString()
    }


    private fun calculate(): String {
        val split = currentInput.split(Regex("(?<=[+\\-*/])|(?=[+\\-*/])"))
            .filter { it.isNotEmpty() }

        val tokens = mutableListOf<Any>()
        split.forEach { element ->
            when (element) {
                "*", "/", "-", "+" -> tokens.add(element)
                else -> if (element.contains(".")) {
                    tokens.add(element.toDouble())
                }else{
                    tokens.add(element.toInt())
                }

            }
        }

        val highPriority = mutableListOf<Any>()
        var i = 0
        while (i < tokens.size) {
            when (val token = tokens[i]) {
                is Int -> highPriority.add(token)
                "*", "/" -> {
                    val prev = highPriority.removeLastOrNull() as Number
                    val next = tokens[i + 1] as Number
                    val result = when (token) {
                        "*" -> if (prev is Double || next is Double){
                            prev.toDouble() * next.toDouble()
                        }else{
                            prev.toInt() * next.toInt()
                        }

                        "/" -> {
                            try {
                                if (next == 0 ) throw ArithmeticException("Деление на ноль")
                                if (prev is Double || next is Double){
                                    prev.toDouble() / next.toDouble()
                                }else{
                                    if (prev.toInt()/next.toInt()!=0){
                                        prev.toDouble() / next.toDouble()
                                    }else{
                                        prev.toInt()/next.toInt()
                                    }
                                }
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

        var result: Number = highPriority[0] as? Int ?: (highPriority[0] as Double)
        i = 1
        while (i < highPriority.size) {
            when (highPriority[i]) {
                "+" -> {
                    val next = highPriority[i + 1] as Number
                    result = when {
                        result is Double || next is Double -> result.toDouble() + next.toDouble()
                        else -> result.toInt() + next.toInt()
                    }
                }
                "-" -> {
                    val next = highPriority[i + 1] as Number
                    result = when {
                        result is Double || next is Double -> result.toDouble() - next.toDouble()
                        else -> result.toInt() - next.toInt()
                    }
                }
            }
            i += 2
        }

        return when (result) {
            is Double -> if (result % 1.0 == 0.0) result.toInt().toString() else result.toString()
            else -> result.toString()
        }
    }

    private fun addComma(): String {
        if (lastNumber) currentInput.append(".")
        lastNumber = false
        return currentInput.toString()
    }

    private fun addNumber(number: String): String {
        currentInput.append(number)
        lastNumber = true
        return currentInput.toString()
    }

    private fun addOperation(operation: String): String {
        if (lastNumber) currentInput.append(operation)
        lastNumber = false
        return currentInput.toString()
    }

}