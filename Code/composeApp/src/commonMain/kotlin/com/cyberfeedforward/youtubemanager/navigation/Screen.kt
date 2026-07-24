package com.cyberfeedforward.youtubemanager.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Settings : Screen("settings")
    object About : Screen("about")
}
