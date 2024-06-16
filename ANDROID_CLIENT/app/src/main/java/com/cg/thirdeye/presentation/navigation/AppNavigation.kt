package com.cg.thirdeye.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cg.thirdeye.presentation.capture_screen.CaptureScreenComposable
import com.cg.thirdeye.presentation.home_screen.HomeScreenComposable

@Composable
fun AppNavComposable(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationRoute.HOME.route
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        composable(NavigationRoute.HOME.route) {
            HomeScreenComposable(navHostController = navController)
        }
        composable(NavigationRoute.CAPTURE.route) {
            CaptureScreenComposable(navHostController = navController)
        }
    }
}