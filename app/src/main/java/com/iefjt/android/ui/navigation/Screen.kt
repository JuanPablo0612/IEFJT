package com.iefjt.android.ui.navigation

sealed class Screen(val route: String) {
    data object Elements: Screen(route = "elements")
    data object ElementDetails: Screen(route = "element_details")
    data object AddElement: Screen("add_element")
}