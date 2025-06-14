package com.example.calculatorapp

import junit.framework.TestCase.assertEquals
import org.junit.Test



class CalculatorTest {
    private var currentInput = StringBuilder()
    private val calculator = CalculateExpression()

    //Sum
    @Test
    fun `test sum`() {
        currentInput = StringBuilder("2+3")
        assertEquals("5", calculator.calculate(currentInput))
    }

    @Test
    fun `test int plus double`() {
        currentInput = StringBuilder("2+2.2")
        assertEquals("4,2", calculator.calculate(currentInput))
    }

    @Test
    fun `test double plus double`() {
        currentInput = StringBuilder("2.1+5.4")
        assertEquals("7,5", calculator.calculate(currentInput))
    }


    //Subtraction
    @Test
    fun `test subtraction`() {
        currentInput = StringBuilder("2-1")
        assertEquals("1", calculator.calculate(currentInput))
    }

    @Test
    fun `test int subtract double`() {
        currentInput = StringBuilder("5-2.2")
        assertEquals("2,8", calculator.calculate(currentInput))
    }

    @Test
    fun `test double subtract double`() {
        currentInput = StringBuilder("10.1-5.4")
        assertEquals("4,7", calculator.calculate(currentInput))
    }

    @Test
    fun `test Subtract with negative result`() {
        currentInput = StringBuilder("6.1-8.7")
        assertEquals("-2,6", calculator.calculate(currentInput))
    }


    //Multiplication
    @Test
    fun `test multiplication`() {
        currentInput = StringBuilder("5*2")
        assertEquals("10", calculator.calculate(currentInput))
    }

    @Test
    fun `test int multiplication double`() {
        currentInput = StringBuilder("6*2.2")
        assertEquals("13,2", calculator.calculate(currentInput))
    }

    @Test
    fun `test double multiplication double`() {
        currentInput = StringBuilder("10.3*5.4")
        assertEquals("55,62", calculator.calculate(currentInput))
    }

    //Division
    @Test
    fun `test division`() {
        currentInput = StringBuilder("10/2")
        assertEquals("5", calculator.calculate(currentInput))
    }

    @Test
    fun `test int division double`() {
        currentInput = StringBuilder("5/2.2")
        assertEquals("2,27272727", calculator.calculate(currentInput))
    }

    @Test
    fun `test double division double`() {
        currentInput = StringBuilder("10.1/5.4")
        assertEquals("1,87037037", calculator.calculate(currentInput))
    }

    @Test
    fun `test division by zero`() {
        currentInput = StringBuilder("5.1/0")
        assertEquals("Ошибка: Деление на ноль", calculator.calculate(currentInput))
    }


    //Large numbers
    @Test
    fun `test large numbers`(){
        currentInput = StringBuilder("999999*999999")
        assertEquals("999998000001",calculator.calculate(currentInput))
    }

    //Complex expression
    @Test
    fun `test sum and multiplication priority`(){
        currentInput = StringBuilder("2+3*4")
        assertEquals("14",calculator.calculate(currentInput))
    }
    @Test
    fun `test division before multiplication`(){
        currentInput = StringBuilder("8/2*3")
        assertEquals("12",calculator.calculate(currentInput))
    }
    @Test
    fun `test complex expression`(){
        currentInput = StringBuilder("2+3*4-10/2")
        assertEquals("9",calculator.calculate(currentInput))
    }
    @Test
    fun `test expression with multiple division`(){
        currentInput = StringBuilder("20/2/5")
        assertEquals("2",calculator.calculate(currentInput))
    }

}

