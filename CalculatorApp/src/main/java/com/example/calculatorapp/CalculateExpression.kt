package com.example.calculatorapp

import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.Stack

/**
 * Класс CalculateExpression отвечает за обработку и вычисление математических выражений,
 * введённых пользователем в виде строки.
 *
 * Он реализует алгоритм Дейкстры (Shunting Yard Algorithm) для преобразования выражения
 * из инфиксной записи (например, 2 + 3 * 4) в постфиксную (обратную польскую запись),
 * где приоритет операций можно легко соблюсти при вычислении (например, 2 3 4 * +).
 *
 * Класс состоит из следующих этапов:
 * 1. Токенизация строки выражения (разделение на числа, операторы, скобки).
 * 2. Преобразование в постфиксную нотацию по алгоритму Shunting Yard.
 * 3. Вычисление результата на основе постфиксной записи.
 * 4. Форматирование результата с округлением.
 */
class CalculateExpression{
    /**
     * Главный метод, вызываемый для вычисления значения выражения.
     * Обрабатывает всю цепочку: токенизация → преобразование в RPN → вычисление → форматирование.
     *
     * @param currentInput строка выражения, например: "3 + (4 * 2)"
     * @return Результат в виде строки, например: "11", или сообщение об ошибке
     */
     fun calculate(currentInput:StringBuilder): String {
        return try {
            val tokens = tokenize(currentInput.toString()) // Разбиваем строку на числа и операторы
            val rpn = shuntingYard(tokens)                 // Преобразуем в обратную польскую нотацию
            val result = evaluateRPN(rpn)                  // Вычисляем результат на основе RPN
            formatResult(result)                           // Преобразуем результат в красивый формат
        } catch (e: Exception) {
            "Ошибка: ${e.message}"
        }
    }

    /**
     * Разбивает строку выражения на отдельные элементы (токены): числа, операторы и скобки.
     *
     * Пример:
     * Вход: "3+(4.5*2)"
     * Выход: ["3", "+", "(", "4.5", "*", "2", ")"]
     *
     * @param expression строка выражения
     * @return Список токенов
     */
    private fun tokenize(expression: String): List<String> {
        return Regex("\\d+(\\.\\d+)?|[-+*/()]").findAll(expression)
            .map { it.value }
            .toList()
    }

    /**
     * Реализация алгоритма Дейкстры для преобразования инфиксной записи в постфиксную.
     * Используются два списка: стек операторов и выходной список (очередь).
     *
     * Процесс:
     * - Числа идут сразу в выходной список.
     * - Операторы сравниваются по приоритету (* /> +-).
     * - Скобки используются для изменения приоритета: всё внутри скобок обрабатывается сначала.
     *
     * Пример:
     * Вход: ["3", "+", "4", "*", "2"]
     * Выход: ["3", "4", "2", "*", "+"]
     *
     * @throws IllegalArgumentException если нарушены правила скобок
     * @param tokens Список токенов в инфиксной форме
     * @return Список токенов в RPN (обратной польской нотации)
     */
    private fun shuntingYard(tokens: List<String>): List<String> {
        val output = mutableListOf<String>()                            // Очередь вывода (RPN)
        val operators = Stack<String>()                                 // Стек операторов
        val precedence = mapOf("+" to 1, "-" to 1, "*" to 2, "/" to 2)  // Приоритеты

        for (token in tokens) {
            when {
                // Если число — сразу в выходной список
                token.matches(Regex("\\d+(\\.\\d+)?")) -> output.add(token)

                // Если оператор
                token in precedence -> {
                    // Пока верхний оператор в стеке имеет больший или равный приоритет
                    // — переносим его в выходной список
                    while (operators.isNotEmpty() && operators.peek() != "(" &&
                        precedence[operators.peek()]!! >= precedence[token]!!
                    ) {
                        output.add(operators.pop())
                    }
                    operators.push(token)
                }
                // Если открывающая скобка — просто кладем в стек
                token == "(" -> operators.push(token)
                // Если закрывающая скобка — переносим всё до открывающей
                token == ")" -> {
                    while (operators.isNotEmpty() && operators.peek() != "(") {
                        output.add(operators.pop())
                    }
                    if (operators.isEmpty() || operators.pop() != "(") {
                        throw IllegalArgumentException("Несоответствие скобок")
                    }
                }
            }
        }

        // После завершения выражения переносим все оставшиеся операторы в выходной список
        while (operators.isNotEmpty()) {
            val op = operators.pop()
            if (op == "(" || op == ")") {
                throw IllegalArgumentException("Несоответствие скобок")
            }
            output.add(op)
        }

        return output
    }

    /**
     * Вычисляет значение выражения, представленного в обратной польской записи (RPN).
     *
     * Алгоритм:
     * - Числа кладутся в стек.
     * - При встрече оператора берём два последних числа из стека, применяем операцию и
     *   результат кладём обратно.
     *
     * Пример:
     * Вход: ["3", "4", "2", "*", "+"]
     * Шаги:
     * 1. стек: [3]
     * 2. стек: [3, 4]
     * 3. стек: [3, 4, 2]
     * 4. операция *: стек → [3, 8]
     * 5. операция +: стек → [11]
     *
     * @throws ArithmeticException при делении на ноль
     * @throws IllegalArgumentException при ошибке в структуре выражения
     * @param tokens Список RPN-токенов
     * @return результат в виде Double
     */
    private fun evaluateRPN(tokens: List<String>): Double {
        val stack = Stack<Double>()

        for (token in tokens) {
            when {
                token.matches(Regex("\\d+(\\.\\d+)?")) -> stack.push(token.toDouble())
                token in listOf("+", "-", "*", "/") -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    val result = when (token) {
                        "+" -> a + b
                        "-" -> a - b
                        "*" -> a * b
                        "/" -> {
                            if (b == 0.0) throw ArithmeticException("Деление на ноль")
                            a / b
                        }
                        else -> throw IllegalArgumentException("Неизвестный оператор: $token")
                    }
                    stack.push(result)
                }
            }
        }

        if (stack.size != 1) throw IllegalArgumentException("Неверное выражение")

        return stack.pop()
    }

    /**
     * Форматирует итоговый результат:
     * - Убирает лишние нули (например, 5.0 → 5)
     * - Ограничивает число знаков после запятой
     * - Использует точку как десятичный разделитель
     *
     * @param value результат выражения
     * @return строковое представление результата
     */
    private fun formatResult(value: Double): String {
        val df = DecimalFormat("#.########")
        df.roundingMode = RoundingMode.HALF_UP
        return df.format(value)
    }
}