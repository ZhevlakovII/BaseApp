package com.izhxx.baseapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.izhxx.baseapp.ui.HomeViewModel
import com.izhxx.baseapp.ui.screens.*
import com.izhxx.baseapp.ui.screens.home.HomeScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    NavHost(
        navController = navController,
        route = NavigationRoots.mainRoot,
        startDestination = NavigationLinks.homeScreen
    ) {
        composable(route = NavigationLinks.homeScreen) {
            HomeScreen(homeViewModel)
        }
        composable(route = NavigationLinks.mapScreen) { MapScreen() }

        composable(route = NavigationLinks.bookingScreen) { BookingScreen() }

        composable(route = NavigationLinks.chatScreen) { ChatScreen() }

        composable(route = NavigationLinks.profileScreen) { ProfileScreen() }
    }
}