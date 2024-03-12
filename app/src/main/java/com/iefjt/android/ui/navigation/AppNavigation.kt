package com.iefjt.android.ui.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.iefjt.android.ui.inventory.elements.add.AddElementScreen
import com.iefjt.android.ui.inventory.elements.details.ElementDetailsScreen
import com.iefjt.android.ui.inventory.elements.list.ElementsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Elements.route) {
        composable(
            route = Screen.Elements.route,
            enterTransition = {
                slideInHorizontally { height -> height }
            },
            exitTransition = {
                slideOutHorizontally { height -> -height }
            },
            popEnterTransition = {
                slideInHorizontally { height -> -height }
            },
            popExitTransition = {
                slideOutHorizontally { height -> height }
            }
        ) {
            ElementsScreen(navController = navController)
        }

        composable(
            route = "${Screen.ElementDetails.route}/{elementId}",
            arguments = listOf(navArgument(name = "elementId") { type = NavType.StringType }),
            enterTransition = {
                slideInHorizontally { height -> height }
            },
            exitTransition = {
                slideOutHorizontally { height -> -height }
            },
            popEnterTransition = {
                slideInHorizontally { height -> -height }
            },
            popExitTransition = {
                slideOutHorizontally { height -> height }
            }
        ) { backStackEntry ->
            ElementDetailsScreen(
                elementId = backStackEntry.arguments?.getString("elementId") ?: "",
                navController = navController
            )
        }

        composable(
            route = Screen.AddElement.route,
            enterTransition = {
                slideInHorizontally { height -> height }
            },
            exitTransition = {
                slideOutHorizontally { height -> -height }
            },
            popEnterTransition = {
                slideInHorizontally { height -> -height }
            },
            popExitTransition = {
                slideOutHorizontally { height -> height }
            }
        ) {
            AddElementScreen(navController = navController)
        }
    }
}