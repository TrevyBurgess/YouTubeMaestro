package com.cyberfeedforward.youtubevideomanager.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cyberfeedforward.youtubevideomanager.viewmodel.SettingsUiState
import com.cyberfeedforward.youtubevideomanager.viewmodel.ThemeMode

@Composable
fun SettingsScreen(
    state: SettingsUiState,
    onToggleNotifications: () -> Unit,
    onThemeModeChange: (ThemeMode) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = state.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Enable Notifications")
            Switch(
                checked = state.isNotificationsEnabled,
                onCheckedChange = { onToggleNotifications() }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Theme Mode",
            style = MaterialTheme.typography.titleMedium
        )

        ThemeMode.entries.forEach { mode ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = state.themeMode == mode,
                    onClick = { onThemeModeChange(mode) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = mode.name.lowercase().replaceFirstChar { it.uppercase() }
                )
            }
        }
    }
}
