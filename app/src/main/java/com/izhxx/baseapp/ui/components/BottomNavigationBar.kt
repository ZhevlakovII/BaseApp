package com.izhxx.baseapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.izhxx.baseapp.navigation.NavigationLinks

sealed class BottomNavigationBar(
    val route: String,
    val title: String,
    val description: String,
    val icon: ImageVector
) {
    object Home: BottomNavigationBar(
        route = NavigationLinks.homeScreen,
        title = "Home",
        description = "Home Screen",
        icon = Icons.Default.Home
    )

    object Map: BottomNavigationBar(
        route = NavigationLinks.mapScreen,
        title = "Map",
        description = "MapScreen",
        icon = Icons.Default.Map
    )

    object Booking: BottomNavigationBar(
        route = NavigationLinks.bookingScreen,
        title = "Booking",
        description = "Booking Screen",
        icon = Icons.Default.Hotel
    )

    object Chat: BottomNavigationBar(
        route = NavigationLinks.chatScreen,
        title = "Chat",
        description = "Chat Screen",
        icon = Icons.Default.Chat
    )

    object Profile: BottomNavigationBar(
        route = NavigationLinks.profileScreen,
        title = "Profile",
        description = "Profile Screen",
        icon = Icons.Default.Person
    )
}