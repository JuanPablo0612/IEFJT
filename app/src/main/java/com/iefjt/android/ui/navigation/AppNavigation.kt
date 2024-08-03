package com.iefjt.android.ui.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.iefjt.android.ui.access.AccessScreen
import com.iefjt.android.ui.addelement.AddElementScreen
import com.iefjt.android.ui.editelement.EditElementScreen
import com.iefjt.android.ui.home.HomeScreen

@Composable
fun AppNavigation(viewModel: NavigationViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState
    val navController = rememberNavController()

    if (uiState.startDestination.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    } else {
        NavHost(navController = navController, startDestination = uiState.startDestination) {
            composable(
                route = Screen.Access.route,
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
                AccessScreen(navController = navController)
            }

            composable(
                route = Screen.Home.route,
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
                HomeScreen(navController = navController)
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
                EditElementScreen(
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
}