package com.cyberfeedforward.youtubevideomanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import com.cyberfeedforward.youtubevideomanager.util.AuthUtils
import com.cyberfeedforward.youtubevideomanager.util.Config

data class HomeUiState(
    val title: String = "Home Screen",
    val greeting: String = "Welcome to the Home Page!",
    val isSignInDialogOpen: Boolean = false,
    val isAuthenticating: Boolean = false,
    val authUrl: String? = null
)

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onSignInClicked() {
        _uiState.value = _uiState.value.copy(isSignInDialogOpen = true)
    }

    fun onDismissDialog() {
        _uiState.value = _uiState.value.copy(isSignInDialogOpen = false)
    }

    fun performYouTubeSignIn() {
        _uiState.value = _uiState.value.copy(isSignInDialogOpen = false)
        val url = AuthUtils.getAuthUrl(Config.GOOGLE_CLIENT_ID, Config.REDIRECT_URI)
        _uiState.value = _uiState.value.copy(authUrl = url)
    }

    fun onAuthUrlHandled() {
        _uiState.value = _uiState.value.copy(authUrl = null, isAuthenticating = true)
        
        // Simulating the flow continues after browser opens. 
        // In a real app, you'd wait for a redirect back to the app/server.
        viewModelScope.launch {
            delay(5000) // Simulating wait for user to sign in in browser
            _uiState.value = _uiState.value.copy(
                isAuthenticating = false,
                greeting = "Browser opened for sign-in. Check your browser to complete the process."
            )
        }
    }
}
