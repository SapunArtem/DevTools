package com.example.pizzashop.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.pizzashop.model.Pizza
import com.example.pizzashop.repository.MenuRepository
import com.example.pizzashop.repository.PizzaRepository
import com.example.pizzashop.ui.screens.MainScreens.BasketScreen
import com.example.pizzashop.ui.screens.MainScreens.DetailsScreen
import com.example.pizzashop.ui.screens.MainScreens.MenuScreen
import com.example.pizzashop.ui.screens.MainScreens.PizzaScreen
import com.example.pizzashop.ui.screens.MenuScreens.AboutScreen
import com.example.pizzashop.ui.screens.MenuScreens.ProfileScreen
import com.example.pizzashop.ui.screens.MenuScreens.SettingsScreen

sealed class Screen(val route: String, val title: String) {
    object Pizza : Screen("pizza_screen", "Пицца")
    object Basket : Screen("basket_screen", "Корзина")
    object Details : Screen("details_screen/{pizzaId}", "О проукте") {
        fun createRoute(pizzaId: Int) = "details_screen/$pizzaId"
    }

    object MenuRoot : Screen("menu_root", "Меню")
    object MenuMain : Screen("menu_main", "Главное")
    object MenuProfile : Screen("menu_profile", "Профиль")
    object MenuSettings : Screen("menu_settings", "Настройки")
    object MenuAboutScreen : Screen("menu_about", "О приложении")

}


@Composable
fun PizzaNavigation(
    navController: NavHostController,
    modifier: Modifier,
    basketList: MutableList<Pizza>
) {


    NavHost(
        navController = navController,
        startDestination = Screen.Pizza.route,
        modifier = modifier
    ) {
        composable(Screen.Pizza.route) {
            PizzaScreen(
                onPizzaClick = { pizzaId ->
                    navController.navigate(Screen.Details.createRoute(pizzaId))
                },
                onAddToBasket = { pizza ->
                    basketList.add(pizza)
                }
            )
        }
        composable(Screen.Basket.route) {
            BasketScreen(
                pizzas = basketList,
                onRemoveClick = { pizza ->
                    basketList.remove(pizza)
                }
            )
        }
        composable(
            Screen.Details.route,
            arguments = listOf(navArgument("pizzaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val pizzaId = backStackEntry.arguments?.getInt("pizzaId") ?: return@composable
            val pizza = PizzaRepository.getPizzaById(pizzaId) ?: return@composable

            DetailsScreen(
                pizza = pizza,
                onAddToBasketClick = {
                    basketList.add(pizza)
                },
            )
        }


        navigation(
            startDestination = Screen.MenuMain.route,
            route = Screen.MenuRoot.route
        ){
            composable(Screen.MenuMain.route) {
                MenuScreen(
                    navController = navController
                )
            }

            MenuRepository.menuItems.forEach { menuItem ->
                composable(menuItem.route){
                    when(menuItem.route){
                        Screen.MenuProfile.route -> ProfileScreen()
                        Screen.MenuAboutScreen.route -> AboutScreen()
                        Screen.MenuSettings.route -> SettingsScreen()
                    }
                }
            }
        }



    }

}
