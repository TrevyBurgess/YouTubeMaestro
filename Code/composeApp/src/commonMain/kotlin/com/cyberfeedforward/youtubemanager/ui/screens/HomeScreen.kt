package com.cyberfeedforward.youtubemanager.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cyberfeedforward.youtubemanager.viewmodel.HomeUiState

@Composable
fun HomeScreen(
    state: HomeUiState,
    onSignInClicked: () -> Unit,
    onDismissDialog: () -> Unit,
    onConfirmSignIn: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = state.title,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = state.greeting,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onSignInClicked,
                enabled = !state.isAuthenticating
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Sign In with YouTube")
            }
        }

        if (state.isAuthenticating) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center).size(48.dp)
            )
        }
    }

    if (state.isSignInDialogOpen) {
        AlertDialog(
            onDismissRequest = onDismissDialog,
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            title = { Text("Manage YouTube Videos") },
            text = { 
                Text(
                    "This app needs permission to manage your YouTube videos. " +
                    "You will be redirected to Google to sign in and grant the 'youtube.upload' permission."
                ) 
            },
            confirmButton = {
                TextButton(onClick = onConfirmSignIn) {
                    Text("Sign In")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissDialog) {
                    Text("Cancel")
                }
            }
        )
    }
}
