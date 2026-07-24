package com.cyberfeedforward.youtubemanager.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class ThemeMode {
    LIGHT, DARK, SYSTEM
}

data class SettingsUiState(
    val title: String = "Settings Screen",
    val isNotificationsEnabled: Boolean = true,
    val themeMode: ThemeMode = ThemeMode.SYSTEM
)

class SettingsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun toggleNotifications() {
        _uiState.value = _uiState.value.copy(
            isNotificationsEnabled = !_uiState.value.isNotificationsEnabled
        )
    }

    fun setThemeMode(mode: ThemeMode) {
        _uiState.value = _uiState.value.copy(
            themeMode = mode
        )
    }
}
