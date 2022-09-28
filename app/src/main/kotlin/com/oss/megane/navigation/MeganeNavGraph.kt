package com.oss.megane.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.oss.megane.ui.account.AccountScreen
import com.oss.megane.ui.home.HomeScreen
import com.oss.megane.ui.home.MovieDetailScreen
import com.oss.megane.ui.util.WindowSize

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    windowSize: WindowSize
) {
    NavHost(navController, startDestination = BottomNavItem.Home.route, modifier = modifier) {
        // Home tab has it's own nav graph
        homeNavGraph(windowSize = windowSize, navController)
        // Account tab doesn't have it's own nav graph
        composable(BottomNavItem.Account.route) {
            AccountScreen()
        }
    }
}

/**
 * Navigation graph of the Home bottom nav item
 */
fun NavGraphBuilder.homeNavGraph(
    windowSize: WindowSize,
    navController: NavHostController
) {
    navigation(
        route = BottomNavItem.Home.route,
        startDestination = "movies"
    ) {
        composable(route = "movies") {
            HomeScreen(windowSize) { navController.navigate("movie/$it") }
        }
        composable(
            route = "movie/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) {
            MovieDetailScreen(movieId = it.arguments?.getString("movieId") ?: "-1")
        }
    }
}
