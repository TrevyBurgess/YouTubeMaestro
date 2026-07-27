package com.cyberfeedforward.youtubemanager.util

object AuthUtils {
    private const val AUTH_ENDPOINT = "https://accounts.google.com/o/oauth2/v2/auth"
    
    // Scopes required to manage uploaded videos
    val SCOPES = listOf(
        "https://www.googleapis.com/auth/youtube.upload",
        "https://www.googleapis.com/auth/youtube",
        "https://www.googleapis.com/auth/userinfo.profile",
        "https://www.googleapis.com/auth/userinfo.email"
    )

    fun getAuthUrl(clientId: String, redirectUri: String): String {
        val scopeString = SCOPES.joinToString(" ")
        return "$AUTH_ENDPOINT?" +
                "client_id=$clientId&" +
                "redirect_uri=$redirectUri&" +
                "response_type=code&" +
                "scope=$scopeString&" +
                "access_type=offline&" +
                "prompt=consent"
    }
}
