package com.iefjt.android.ui.home

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iefjt.android.R
import com.iefjt.android.ui.common.ScaffoldContent
import com.iefjt.android.ui.home.elementlist.ElementListTab
import com.iefjt.android.ui.home.users.UsersTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    val uiState = viewModel.uiState
    val homeNavController = rememberNavController()
    val userRole = uiState.user.role

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        },
        bottomBar = {
            if (userRole == "admin") {
                BottomAppBar(actions = {
                    homeAdminScreens.forEach { tab ->
                        NavigationBarItem(
                            selected = homeNavController.currentDestination!!.route == tab.route,
                            onClick = {
                                if (navController.currentBackStackEntry?.destination?.route != tab.route) {
                                    if (homeNavController.currentBackStackEntry?.destination?.route == tab.route) {
                                        homeNavController.navigateUp()
                                    } else {
                                        homeNavController.navigate(tab.route)
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = stringResource(tab.titleId)
                                )
                            },
                            label = { Text(stringResource(tab.titleId)) },
                            alwaysShowLabel = false
                        )
                    }
                })
            }
        }
    ) { padding ->
        ScaffoldContent(padding = padding) {
            NavHost(
                navController = homeNavController,
                startDestination = HomeScreens.Elements.route
            ) {
                val screens = when (userRole) {
                    "admin", "editor" -> homeAdminScreens
                    else -> homeUserScreens
                }

                screens.forEach { tab ->
                    composable(
                        route = tab.route,
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
                        when (tab.route) {
                            HomeScreens.Elements.route -> ElementListTab(
                                allowEdit = userRole != "user",
                                navController = navController
                            )

                            HomeScreens.Users.route -> UsersTab()
                        }
                    }
                }
            }
        }
    }
}

sealed class HomeScreens(
    val route: String,
    val titleId: Int,
    val icon: ImageVector
) {
    data object Elements :
        HomeScreens("elements", R.string.inventory_items, Icons.AutoMirrored.Default.List)

    data object Users : HomeScreens("users", R.string.users, Icons.Filled.People)
}

private
val homeAdminScreens = listOf(
    HomeScreens.Elements,
    HomeScreens.Users
)

private
val homeUserScreens = listOf(
    HomeScreens.Elements
)
