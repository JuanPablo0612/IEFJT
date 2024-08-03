package com.iefjt.android.ui.navigation

sealed class Screen(val route: String) {
    data object Access: Screen(route = "access")
    data object Home: Screen(route = "home")
    data object ElementDetails: Screen(route = "element_details")
    data object AddElement: Screen("add_element")
}