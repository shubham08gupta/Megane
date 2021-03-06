package com.oss.megane.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oss.megane.ui.account.AccountScreen
import com.oss.megane.ui.home.HomeScreen
import com.oss.megane.ui.util.WindowSize

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    windowSize: WindowSize
) {
    NavHost(navController, startDestination = BottomNavItem.Home.route, modifier = modifier) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(windowSize)
        }
        composable(BottomNavItem.Account.route) {
            AccountScreen()
        }
    }
}
