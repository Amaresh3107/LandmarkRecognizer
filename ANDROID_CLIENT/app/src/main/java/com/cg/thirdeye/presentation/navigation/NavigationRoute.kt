package com.cg.thirdeye.presentation.navigation

sealed class NavigationRoute(val route: String) {
    data object HOME : NavigationRoute(Screen.HOME.name)
    data object CAPTURE : NavigationRoute(Screen.CAPTURE.name)
}