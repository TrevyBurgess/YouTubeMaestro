package com.cyberfeedforward.youtubemaestro

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cyberfeedforward.youtubemaestro.navigation.Screen
import com.cyberfeedforward.youtubemaestro.ui.screens.AboutScreen
import com.cyberfeedforward.youtubemaestro.ui.screens.HomeScreen
import com.cyberfeedforward.youtubemaestro.ui.screens.SettingsScreen
import com.cyberfeedforward.youtubemaestro.viewmodel.AboutViewModel
import com.cyberfeedforward.youtubemaestro.viewmodel.HomeViewModel
import com.cyberfeedforward.youtubemaestro.viewmodel.SettingsViewModel
import com.cyberfeedforward.youtubemaestro.viewmodel.ThemeMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.isSystemInDarkTheme

@Composable
@Preview
fun App() {
    val settingsViewModel: SettingsViewModel = viewModel { SettingsViewModel() }
    val settingsState by settingsViewModel.uiState.collectAsState()
    
    val isDark = when (settingsState.themeMode) {
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
    }

    val colorScheme = if (isDark) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    MaterialTheme(colorScheme = colorScheme) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                NavigationBar {
                    val items = listOf(
                        Screen.Home to Icons.Default.Home,
                        Screen.Settings to Icons.Default.Settings,
                        Screen.About to Icons.Default.Info
                    )
                    items.forEach { (screen, icon) ->
                        val selected = currentDestination?.route == screen.route
                        NavigationBarItem(
                            icon = { Icon(icon, contentDescription = screen.route) },
                            label = { Text(screen.route.replaceFirstChar { it.uppercase() }) },
                            selected = selected,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().route!!) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.route) {
                    val viewModel: HomeViewModel = viewModel { HomeViewModel() }
                    val state by viewModel.uiState.collectAsState()
                    HomeScreen(
                        state = state,
                        onSignInClicked = { viewModel.onSignInClicked() },
                        onDismissDialog = { viewModel.onDismissDialog() },
                        onConfirmSignIn = { viewModel.performYouTubeSignIn() },
                        onAuthUrlHandled = { viewModel.onAuthUrlHandled() }
                    )
                }
                composable(Screen.Settings.route) {
                    SettingsScreen(
                        state = settingsState,
                        onToggleNotifications = { settingsViewModel.toggleNotifications() },
                        onThemeModeChange = { settingsViewModel.setThemeMode(it) }
                    )
                }
                composable(Screen.About.route) {
                    val viewModel: AboutViewModel = viewModel { AboutViewModel() }
                    val state by viewModel.uiState.collectAsState()
                    AboutScreen(state = state)
                }
            }
        }
    }
}
