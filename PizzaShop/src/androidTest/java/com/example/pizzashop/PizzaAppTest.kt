package com.example.pizzashop

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class PizzaAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<PizzaShop>()


    @Test
    fun navigationFlow() {
        detailScreenNavigate()
        basketScreenNavigate()
        menuScreenNavigate()

    }

    // Тест навигации на экран Details по нажатию на карточку пиццы
    @Test
    fun detailScreenNavigate() {
        composeTestRule.onNodeWithText("Пицца").assertExists()
        composeTestRule.onNodeWithTag("pizza_1").performClick()
        composeTestRule.onNodeWithText("О продукте").assertExists()
        composeTestRule.onNodeWithText("Ранч пицца").assertExists()
        composeTestRule.onNodeWithTag("назад").performClick()
        composeTestRule.onNodeWithText("Пицца").assertExists()
    }

    // Тест навигации на экран Basket
    @Test
    fun basketScreenNavigate() {
        composeTestRule.onNodeWithText("Пицца").assertExists()
        composeTestRule.onNodeWithText("Basket").performClick()
        composeTestRule.onNodeWithText("Корзина пуста").assertExists()
        composeTestRule.onNodeWithTag("назад").performClick()
        composeTestRule.onNodeWithText("Пицца").assertExists()
    }

    // Тест навигации на экран Menu и всех экранов в Menu
    @Test
    fun menuScreenNavigate() {
        composeTestRule.onNodeWithText("Пицца").assertExists()
        composeTestRule.onNodeWithText("Menu").performClick()
        composeTestRule.onNodeWithText("Главное").assertExists()

        menuProfileScreenNavigate()
        menuSettingsScreenNavigate()
        menuAboutScreenNavigate()

        composeTestRule.onNodeWithText("Pizza").performClick()
        composeTestRule.onNodeWithText("Пицца").assertExists()
    }
    //Метод для проверки навигации на экран Profile
    fun menuProfileScreenNavigate() {
        composeTestRule.onNodeWithText("Главное").assertExists()
        composeTestRule.onNodeWithText("Профиль").performClick()
        composeTestRule.onNodeWithText("Профиль").assertExists()
        composeTestRule.onNodeWithTag("назад").performClick()
        composeTestRule.onNodeWithText("Главное").assertExists()
    }

    //Метод для проверки навигации на экран Settings
    fun menuSettingsScreenNavigate() {
        composeTestRule.onNodeWithText("Главное").assertExists()
        composeTestRule.onNodeWithText("Настройки").performClick()
        composeTestRule.onNodeWithText("Настройки").assertExists()
        composeTestRule.onNodeWithTag("назад").performClick()
        composeTestRule.onNodeWithText("Главное").assertExists()
    }

    // Метод для проверки навигации на экран About
    fun menuAboutScreenNavigate() {
        composeTestRule.onNodeWithText("Главное").assertExists()
        composeTestRule.onNodeWithText("О приложении").performClick()
        composeTestRule.onNodeWithText("О приложении").assertExists()
        composeTestRule.onNodeWithTag("назад").performClick()
        composeTestRule.onNodeWithText("Главное").assertExists()
    }


    // Тест навигации BottomNavigationBar
    @Test
    fun mainNavigationBottomMenu() {
        composeTestRule.onNodeWithText("Пицца").assertIsDisplayed()
        composeTestRule.onNodeWithText("Basket").performClick()
        composeTestRule.onNodeWithText("Корзина пуста").assertIsDisplayed()
        composeTestRule.onNodeWithText("Menu").performClick()
        composeTestRule.onNodeWithText("Главное").assertIsDisplayed()
        composeTestRule.onNodeWithText("Pizza").performClick()
        composeTestRule.onNodeWithText("Пицца").assertIsDisplayed()
    }


    // Тест добовление пиццы в корзину с экрана Pizza
    @Test
    fun addToBasketFromPizzaScreen() {
        composeTestRule.onNodeWithText("Ранч пицца").assertExists()
        composeTestRule.onNodeWithTag("add_btn_1").performClick()
        composeTestRule.onNodeWithText("Basket").performClick()
        composeTestRule.onNodeWithText("Ранч пицца").assertExists()
    }

    // Тест добавление пиццы в корзину с экрана Details
    @Test
    fun addToBasketFromDetailsScreen(){
        composeTestRule.onNodeWithText("Пицца").assertExists()
        composeTestRule.onNodeWithTag("pizza_2").performClick()
        composeTestRule.onNodeWithText("О продукте").assertExists()
        composeTestRule.onNodeWithTag("add_btn").performClick()
        composeTestRule.onNodeWithText("Basket").performClick()
        composeTestRule.onNodeWithText("Гавайская").assertExists()
    }

    // Тест на удаление пиццы из корзины
    @Test
    fun removePizzaFromBasket(){
        composeTestRule.onNodeWithText("Ранч пицца").assertExists()
        composeTestRule.onNodeWithTag("add_btn_1").performClick()
        composeTestRule.onNodeWithText("Гавайская").assertExists()
        composeTestRule.onNodeWithTag("add_btn_2").performClick()

        composeTestRule.onNodeWithText("Basket").performClick()
        composeTestRule.onNodeWithText("Ранч пицца").assertExists()
        composeTestRule.onNodeWithText("Гавайская").assertExists()

        composeTestRule.onAllNodesWithTag("удалить")[0].performClick()
        composeTestRule.onAllNodesWithTag("удалить")[0].performClick()

        composeTestRule.onNodeWithText("Корзина пуста")
    }

}