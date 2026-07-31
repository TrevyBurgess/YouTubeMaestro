package com.cyberfeedforward.youtubevideomanager.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AboutUiState(
    val title: String = "About Screen",
    val appName: String = "YouTube Video Manager",
    val version: String = "1.0.0",
    val developer: String = "Cyber Feedforward"
)

class AboutViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AboutUiState())
    val uiState: StateFlow<AboutUiState> = _uiState.asStateFlow()
}
