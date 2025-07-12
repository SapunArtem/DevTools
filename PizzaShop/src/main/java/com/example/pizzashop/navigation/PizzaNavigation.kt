package com.example.pizzashop.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.example.pizzashop.model.Pizza
import com.example.pizzashop.repository.PizzaRepository
import com.example.pizzashop.ui.screens.MainScreens.BasketScreen
import com.example.pizzashop.ui.screens.MainScreens.DetailsScreen
import com.example.pizzashop.ui.screens.MainScreens.MenuScreen
import com.example.pizzashop.ui.screens.MainScreens.PizzaScreen
import com.example.pizzashop.ui.screens.MenuScreens.AboutScreen
import com.example.pizzashop.ui.screens.MenuScreens.ProfileScreen
import com.example.pizzashop.ui.screens.MenuScreens.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
object PizzaRoute

@Serializable
object BasketRoute

@Serializable
data class DetailsRoute(val pizzaId: Int)

@Serializable
object MenuMainRoute

@Serializable
object MenuProfileRoute

@Serializable
object MenuSettingsRoute

@Serializable
object MenuAboutRoute


/**
 *Навигационный компонент приложения
 * @param navController Контроллер навигации
 * @param modifier Модификатор компоновки
 * @param basketList Список пицц в корзине
 */
@Composable
fun PizzaNavigation(
    navController: NavHostController,
    modifier: Modifier,
    basketList: MutableList<Pizza>
) {


    NavHost(
        navController = navController,
        startDestination = PizzaRoute,
        modifier = modifier
    ) {
        // Навигация для экрана PizzaScreen
        composable<PizzaRoute> {
            PizzaScreen(
                onPizzaClick = { pizzaId ->
                    navController.navigate(DetailsRoute(pizzaId))
                },
                onAddToBasket = { pizza ->
                    basketList.add(pizza)
                }
            )
        }
        // Навигация для экрана BasketScreen
        composable<BasketRoute> {
            BasketScreen(
                pizzas = basketList,
                onRemoveClick = { pizza ->
                    basketList.remove(pizza)
                }
            )
        }
        // Навигация для экрана DetailsScreen
        composable<DetailsRoute>(
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://pizzashop.com/pizza/{pizzaId}"
                }
            )) { backStackEntry ->
            val detailsRoute = backStackEntry.toRoute<DetailsRoute>()
            val pizzaId = detailsRoute.pizzaId
            val pizza = PizzaRepository.getPizzaById(pizzaId) ?: return@composable

            DetailsScreen(
                pizza = pizza,
                onAddToBasketClick = {
                    basketList.add(pizza)
                },
            )
        }


        navigation(
            startDestination = MenuMainRoute::class.qualifiedName!!,
            route = "menu_root"
        )
        {
            // Навигация для экрана MenuScreen
            composable<MenuMainRoute> {
                MenuScreen(
                    navController = navController
                )
            }

            // Профиль
            composable<MenuProfileRoute>(
                deepLinks = listOf(
                    navDeepLink { uriPattern = "https://pizzashop.com/menu/profile" }
                )
            ) {
                ProfileScreen()
            }

            // Настройки
            composable<MenuSettingsRoute>(
                deepLinks = listOf(
                    navDeepLink { uriPattern = "https://pizzashop.com/menu/settings" }
                )
            ) {
                SettingsScreen()
            }

            // О приложении
            composable<MenuAboutRoute>(
                deepLinks = listOf(
                    navDeepLink { uriPattern = "https://pizzashop.com/menu/about" }
                )
            ) {
                AboutScreen()
            }

        }


    }

}
