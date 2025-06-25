package com.example.calculatorapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.calculatorapp.UI.CalcActivity
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorE2ETest {

    @get:Rule

    val activity = activityScenarioRule<CalcActivity>()

    @Test
    fun testFullTests(){
        testSum()
        Espresso.onView(ViewMatchers.withId(R.id.btnAC)).perform(ViewActions.click())

        testMinus()
        Espresso.onView(ViewMatchers.withId(R.id.btnAC)).perform(ViewActions.click())

        testMultiplication()
        Espresso.onView(ViewMatchers.withId(R.id.btnAC)).perform(ViewActions.click())

        testDivision()
        Espresso.onView(ViewMatchers.withId(R.id.btnAC)).perform(ViewActions.click())

        testDecimalOperation()
        Espresso.onView(ViewMatchers.withId(R.id.btnAC)).perform(ViewActions.click())

        testComplexExpression()
        Espresso.onView(ViewMatchers.withId(R.id.btnAC)).perform(ViewActions.click())

        testDivisionByZero()
        Espresso.onView(ViewMatchers.withId(R.id.btnAC)).perform(ViewActions.click())

        testDelButton()
        Espresso.onView(ViewMatchers.withId(R.id.btnAC)).perform(ViewActions.click())

        testClearButton()

        testProfileNavigation()

        testHistoryNavigation()

        testHistoryFragmentFunctionality()


    }

    //Проверка операторов +, -, *, /
    //Проверка оператора +
    @Test
    fun testSum(){
        //Пример 5+3 =8
        Espresso.onView(ViewMatchers.withId(R.id.btn5)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnPlus)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn3)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnEquals)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.tvAnswer))
            .check(ViewAssertions.matches(ViewMatchers.withText("=8")))
    }
    //Проверка оператора -
    @Test
    fun testMinus(){
        //Пример 5-3 =2
        Espresso.onView(ViewMatchers.withId(R.id.btn5)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnMinus)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn3)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnEquals)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.tvAnswer))
            .check(ViewAssertions.matches(ViewMatchers.withText("=2")))
    }
    //Проверка оператора *
    @Test
    fun testMultiplication(){
        //Пример 5*3 =15
        Espresso.onView(ViewMatchers.withId(R.id.btn5)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnMultiplication)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn3)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnEquals)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.tvAnswer))
            .check(ViewAssertions.matches(ViewMatchers.withText("=15")))
    }
    //Проверка оператора /
    @Test
    fun testDivision(){
        //Пример 10/5 =2
        Espresso.onView(ViewMatchers.withId(R.id.btn1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn0)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnDivision)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn5)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnEquals)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.tvAnswer))
            .check(ViewAssertions.matches(ViewMatchers.withText("=2")))
    }

    //Проверка комплексного выражения с приоритетом операций
    @Test
    fun testComplexExpression(){
        //Пример 10+9*8/6-2 = 20
        Espresso.onView(ViewMatchers.withId(R.id.btn1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn0)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnPlus)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn9)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnMultiplication)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn8)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnDivision)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn6)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnMinus)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnEquals)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.tvAnswer))
            .check(ViewAssertions.matches(ViewMatchers.withText("=20")))
    }

    //Проверка выражений с использованием точки
    @Test
    fun testDecimalOperation(){
        //Пример 10.1 + 2.3 = 12.4
        Espresso.onView(ViewMatchers.withId(R.id.btn1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn0)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnComma)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnPlus)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnComma)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn3)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnEquals)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.tvAnswer))
            .check(ViewAssertions.matches(ViewMatchers.withText("=12,4")))
    }
    //Проверка деления на 0
    @Test
    fun testDivisionByZero(){
        Espresso.onView(ViewMatchers.withId(R.id.btn2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnDivision)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn0)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnEquals)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.tvAnswer))
            .check(ViewAssertions.matches(ViewMatchers.withText("=Ошибка: Деление на ноль")))
    }



    //Проверка кнопок AC и Del
    //Проверка кнопки AC
    @Test
    fun testClearButton(){
        Espresso.onView(ViewMatchers.withId(R.id.btn5)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnPlus)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn3)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnEquals)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.btnAC)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.tvAnswer))
            .check(ViewAssertions.matches(ViewMatchers.withText("")))
    }
    //Проверка кнопки Del
    @Test
    fun testDelButton(){
        Espresso.onView(ViewMatchers.withId(R.id.btn1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn3)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnDel)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.tvOperation))
            .check(ViewAssertions.matches(ViewMatchers.withText("12")))
    }





    //Тесты навигации по экранам
    //Проверка перехода на экран History
    @Test
    fun testHistoryNavigation(){
        Espresso.onView(ViewMatchers.withId(R.id.btnHistory)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnBack)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.FragmentConteiner))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    //Проверка перехода на экран Profile
    @Test
    fun testProfileNavigation(){
        Espresso.onView(ViewMatchers.withId(R.id.btnProfile)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnBack)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.FragmentConteiner))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    //Полная проверка функциональности фрагмента History
    @Test
    fun testHistoryFragmentFunctionality(){
        //Первое вычисление
        Espresso.onView(ViewMatchers.withId(R.id.btn5)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnPlus)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn5)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnEquals)).perform(ViewActions.click())

        //Очистка экрана
        Espresso.onView(ViewMatchers.withId(R.id.btnAC)).perform(ViewActions.click())

        //Второе вычисление
        Espresso.onView(ViewMatchers.withId(R.id.btn5)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnMultiplication)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn4)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btnEquals)).perform(ViewActions.click())

        //Очистка экрана
        Espresso.onView(ViewMatchers.withId(R.id.btnAC)).perform(ViewActions.click())

        //Открывает историю
        Espresso.onView(ViewMatchers.withId(R.id.btnHistory)).perform(ViewActions.click())

        //Проверяем полученные записи
        Espresso.onView(ViewMatchers.withId(R.id.tvHistory))
            .check(ViewAssertions.matches(ViewMatchers.withText(containsString("5+5 = 10"))))
            .check(ViewAssertions.matches(ViewMatchers.withText(containsString("5*4 = 20"))))

        //Очищаем историю
        Espresso.onView(ViewMatchers.withId(R.id.btnDeleteHistory)).perform(ViewActions.click())

        //Проверяем что история очищена
        Espresso.onView(ViewMatchers.withId(R.id.tvHistory))
            .check(ViewAssertions.matches(ViewMatchers.withText("")))

        //Возвращаемся на основной экран
        Espresso.onView(ViewMatchers.withId(R.id.btnBack)).perform(ViewActions.click())

    }
}